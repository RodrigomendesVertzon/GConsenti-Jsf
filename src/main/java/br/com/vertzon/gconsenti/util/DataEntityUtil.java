package br.com.vertzon.gconsenti.util;

import br.com.vertzon.gconsenti.domain.model.correlation.DataEntity;

public class DataEntityUtil {

	public static DataEntity shuffle(String value) {
		DataEntity dataShuffle = new DataEntity();
		if (value != null) {
			int len = value.length();
			int temp = 0;
			int chars = len / 3;

			for (int i = 0; i < len; i = i + chars) {
				switch (temp) {
				case 0:
					dataShuffle.setAid(value.substring(i, i + chars));
				case 1:
					dataShuffle.setBid(value.substring(i, i + chars));
				case 2:
					if (len % 3 != 0) {
						dataShuffle.setCid(value.substring(i, i + chars) + value.substring(i + chars, len));
					} else {
						dataShuffle.setCid(value.substring(i, i + chars));
					}
				}
				temp++;
			}
		}
		return dataShuffle;
	}

//	public static boolean compare(DataEntity dataEntity) throws JsonMappingException, JsonProcessingException, UnsupportedEncodingException {
//		String dataEntityValue = dataEntity.getAid() + dataEntity.getBid() + dataEntity.getCid();
//		List<String> dataEntities = DataEntity.getAllXDataEntitiesByXid(dataEntityX, dataEntity.getAid(), dataEntity.getBid(), dataEntity.getCid());
//		
//		if(dataEntities.contains(dataEntityValue)) {
//			return true;
//		}
//		return false;
//	}
}
