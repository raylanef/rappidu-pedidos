package br.com.rappidu.domain.entities;

import java.util.List;

public record OrderRequest(String customerName, List<ProductRequest> products) {
}
