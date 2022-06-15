package br.com.vertzon.gconsenti.domain.service;

import java.net.SocketException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vertzon.gconsenti.domain.exception.BusinessRuleException;
import br.com.vertzon.gconsenti.domain.exception.EntityInUseException;
import br.com.vertzon.gconsenti.domain.exception.EntityNotFoundException;
import br.com.vertzon.gconsenti.domain.model.User;
import br.com.vertzon.gconsenti.domain.repository.ErrorMessageRepository;
import br.com.vertzon.gconsenti.domain.repository.UserRepository;
import br.com.vertzon.gconsenti.util.RegexValidators;
import br.com.vertzon.gconsenti.util.RetrieveMacAddress;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private ErrorMessageRepository errorMessagesRepository;

	@Transactional
	public User register(User user) {
		if (!user.isEditing() && userRepository.existsByEmail(user.getEmail())) {
			throw new BusinessRuleException(
					errorMessagesRepository.findByErrorCode("gc-0147"));
		}
		
		try {
			user.setMacAddress(RetrieveMacAddress.getMacAddress());
		} catch (SocketException e) {
			e.getMessage();
		}
		
		if (StringUtils.isEmpty(user.getName())) {
			throw new BusinessRuleException(
					errorMessagesRepository.findByErrorCode("gc-0104"));
		}
		if (StringUtils.isEmpty(user.getEmail())) {
			throw new BusinessRuleException(
					errorMessagesRepository.findByErrorCode("gc-0143"));
		}
		if (StringUtils.isEmpty(user.getPassword())) {
			throw new BusinessRuleException(
					errorMessagesRepository.findByErrorCode("gc-0112"));
		}
		if (user.getPassword().length() < 8) {
			throw new BusinessRuleException(
					errorMessagesRepository.findByErrorCode("gc-0144"));
		}
		if (!user.getPassword().matches(RegexValidators.getPasswordRegex())) {
			throw new BusinessRuleException(
					errorMessagesRepository.findByErrorCode("gc-0145"));
		}
		if (user.getProfile() == null) {
			throw new BusinessRuleException(
					errorMessagesRepository.findByErrorCode("gc-0146"));
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setEnabled(true);
		return userRepository.saveAndFlush(user);
	}

	@Transactional
	public void delete(Long id) {
		try {
			userRepository.deleteById(id);
			userRepository.flush();
		
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
					String.format(errorMessagesRepository.findByErrorCode("gc-0100"), id));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format(errorMessagesRepository.findByErrorCode("gc-0101"), id));
		}
	}

	@Transactional(readOnly = true)
	public List<User> search(User userFilter) {
		Example<User> example = Example.of(userFilter,
				ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
		return userRepository.findAll(example);
	}
}
