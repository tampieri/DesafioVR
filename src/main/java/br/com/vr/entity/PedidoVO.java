package br.com.vr.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.com.vr.emun.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity(name = "tb_Pedido")
public class PedidoVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idPedido")
	private Integer idPedido;
	
	@Column(name = "status")
	private Status status;
	
	@Column(name = "dataVencimento")
	private Date dataVencimento;
	
	@OneToMany(mappedBy="pedido", cascade=CascadeType.ALL)
	private List<ItemVO> itens = new ArrayList<>();

	public PedidoVO(Integer idPedido, Status status, Date dataVencimento) {
		super();
		this.idPedido = idPedido;
		this.status = status;
		this.dataVencimento = dataVencimento;
	}
}
