package br.com.vr.emun;

public enum Status {

	ATIVO(1, "Ativo"), 
	INATIVO(2, "Inativo");
	
	private int cod;
	private String descricao;

	private Status(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Status toEnum(Integer id) {
		if (id == null) {
			return null;
		}
		for (Status x : Status.values()) {
			if (id.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido " + id);
	}
}
