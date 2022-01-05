package com.spring.boot.mc.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_orderedItem")
public class OrderedItem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	@JsonIgnore
	private OrderedItemPK id = new OrderedItemPK();
	
	private Double discount;
	private Integer quantity;
	private Double price;
	
	public OrderedItem() {
		// TODO Auto-generated constructor stub
	}

	public OrderedItem(Order order, Product product, Double discount, Integer quantity, Double price) {
		super();
		id.setOrder(order);
		id.setProduct(product);
		this.discount = discount;
		this.quantity = quantity;
		this.price = price;
	}

	@JsonIgnore
	public Order getOrder() {
		return id.getOrder();
	}
	
	public void setOrder(Order order) {
		id.setOrder(order);
	}
	public Double getSubTotal() {
		return (price - discount) * quantity;
	}
	
	public Product getProduct() {
		return id.getProduct();
	}
	
	public void setProduct(Product product) {
		id.setProduct(product);
	}
	
	public OrderedItemPK getId() {
		return id;
	}

	public void setId(OrderedItemPK id) {
		this.id = id;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderedItem other = (OrderedItem) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		
		StringBuilder builder = new StringBuilder();
		builder.append(getProduct().getName());
		builder.append(" - Quantidade: ");
		builder.append(getQuantity());
		builder.append(", Preço unitário: ");
		builder.append(nf.format(getPrice()));
		builder.append(", Subtotal: ");
		builder.append(nf.format(getSubTotal()));
		builder.append("\n");
		return builder.toString();
	}

	
}
