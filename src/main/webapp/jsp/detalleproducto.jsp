<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detalles de Producto</title>
<style>
	* {
		margin: 0;
		padding: 0;
		box-sizing: border-box;
	}

	body {
		font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, sans-serif;
		background-color: #ffffff;
		color: #333;
		line-height: 1.6;
	}

	. container {
		max-width:  1000px;
		margin: 0 auto;
		padding: 40px 20px;
	}

	. back-link {
		display: inline-block;
		margin-bottom: 30px;
		color: #0066cc;
		text-decoration: none;
		font-size: 14px;
		font-weight:  500;
	}

	.back-link:hover {
		text-decoration:  underline;
	}

	. product-grid {
		display: grid;
		grid-template-columns: 1fr 1fr;
		gap: 40px;
		align-items: start;
	}

	.product-image {
		background-color: #f5f5f5;
		border-radius: 8px;
		overflow: hidden;
		display: flex;
		align-items: center;
		justify-content: center;
	}

	.product-image img {
		width: 100px;
		height: 100px;
		object-fit: cover;
	}

	.product-details h1 {
		font-size: 32px;
		margin-bottom: 20px;
		color: #1a1a1a;
		font-weight: 700;
	}

	.product-price {
		font-size: 28px;
		font-weight: 700;
		color:  #059669;
		margin-bottom:  30px;
	}

	.product-info {
		margin-bottom: 30px;
	}

	.info-row {
		display: flex;
		justify-content: space-between;
		padding: 16px 0;
		border-bottom: 1px solid #e5e5e5;
	}

	.info-row:last-child {
		border-bottom: none;
	}

	.info-label {
		font-weight: 600;
		color: #666;
		min-width: 150px;
	}

	. info-value {
		color: #1a1a1a;
		text-align: right;
		flex: 1;
	}

	.product-description {
		margin-bottom: 30px;
	}

	.product-description h3 {
		font-size: 16px;
		font-weight: 600;
		margin-bottom: 12px;
		color: #1a1a1a;
	}

	.product-description p {
		color: #555;
		line-height: 1.7;
	}

	.availability {
		padding: 12px 16px;
		border-radius:  6px;
		margin-bottom: 30px;
		font-size: 14px;
		font-weight: 500;
	}

	.availability.in-stock {
		background-color: #dcfce7;
		color:  #166534;
	}

	. availability.out-of-stock {
		background-color: #fee2e2;
		color:  #991b1b;
	}


	.divider {
		height: 1px;
		background-color:  #e5e5e5;
		margin: 30px 0;
	}

	@media (max-width: 768px) {
		.product-grid {
			grid-template-columns: 1fr;
			gap: 30px;
		}

		. product-details h1 {
			font-size: 24px;
		}

		. product-price {
			font-size: 24px;
		}

		.info-row {
			flex-direction: column;
			gap: 8px;
		}

		. info-value {
			text-align: left;
		}

	}
</style>
</head>
<body>
	<div class="container">
		<a href="javascript:history.back()" class="back-link">← Volver</a>

		<div class="product-grid">
			<div class="product-image">
				<img src="${producto.imagen}" alt="${producto. nombre}">
			</div>

			<div class="product-details">
				<h1>${producto.nombre}</h1>
				
				<div class="product-price">${producto.precio}</div>

				<div class="product-info">
					<div class="info-row">
						<div class="info-label">ID Producto</div>
						<div class="info-value">${producto.idProducto}</div>
					</div>
					<div class="info-row">
						<div class="info-label">Condición</div>
						<div class="info-value">${producto.condicion}</div>
					</div>
					<div class="info-row">
						<div class="info-label">Disponibilidad</div>
						<div class="info-value">${producto.disponibilidad}</div>
					</div>
				</div>

				<div class="divider"></div>

				<div class="product-description">
					<h3>Descripción</h3>
					<p>${producto.descripcion}</p>
				</div>

				<div class="availability in-stock">
					✓ Disponible para compra
				</div>

			</div>
		</div>
	</div>
</body>
</html>