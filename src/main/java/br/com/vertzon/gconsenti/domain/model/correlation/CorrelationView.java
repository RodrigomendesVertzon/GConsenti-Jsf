package br.com.vertzon.gconsenti.domain.model.correlation;

public class CorrelationView {
	
	public CorrelationView(String banco, String tabela, String coluna, String nome) {
		this.banco = banco;
		this.tabela = tabela;
		this.coluna = coluna;
		this.nome = nome;
	}
	
	private String banco;
	private String tabela;
	private String coluna;
	private String nome;
	
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getTabela() {
		return tabela;
	}
	public void setTabela(String tabela) {
		this.tabela = tabela;
	}
	public String getColuna() {
		return coluna;
	}
	public void setColuna(String coluna) {
		this.coluna = coluna;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
