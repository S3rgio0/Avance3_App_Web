<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Catalogo de Producto</title>
<style>
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }

    body {
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        background-color:  #f5f5f5;
        color: #333;
        padding: 20px;
    }

    div {
        max-width: 1200px;
        margin:  0 auto 30px;
    }

    form {
        background-color: white;
        padding:  20px;
        border-radius:  8px;
        box-shadow:  0 2px 4px rgba(0, 0, 0, 0.1);
    }

    fieldset {
        border: 1px solid #ddd;
        border-radius: 5px;
        padding: 15px;
    }

    legend {
        font-size: 18px;
        font-weight: bold;
        color: #2c3e50;
        padding: 0 10px;
    }

    input[type="text"] {
        padding: 10px;
        border: 1px solid #bbb;
        border-radius: 4px;
        font-size: 14px;
        width: 300px;
        margin-right: 10px;
        transition: border-color 0.3s;
    }

    input[type="text"]:focus {
        outline: none;
        border-color: #3498db;
        box-shadow: 0 0 5px rgba(52, 152, 219, 0.3);
    }

    input[type="submit"] {
        padding: 10px 30px;
        background-color:  #3498db;
        color: white;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        font-size: 14px;
        font-weight:  bold;
        transition: background-color 0.3s;
    }

    input[type="submit"]:hover {
        background-color: #2980b9;
    }

    input[type="submit"]:active {
        transform: scale(0.98);
    }

    table {
        width:  100%;
        max-width: 1200px;
        margin: 0 auto;
        border-collapse: collapse;
        background-color: white;
        box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        border-radius: 8px;
        overflow: hidden;
    }

    thead {
        background-color: #2c3e50;
        color: white;
    }

    thead th {
        padding: 15px;
        text-align: left;
        font-weight: 600;
        font-size: 14px;
    }

    tbody tr {
        border-bottom:  1px solid #ecf0f1;
        transition: background-color 0.3s;
    }

    tbody tr:hover {
        background-color: #f8f9fa;
    }

    tbody td {
        padding: 15px;
        vertical-align: middle;
    }

    tbody img {
        max-width: 100px;
        max-height:  100px;
        object-fit: cover;
        border-radius: 4px;
        transition: transform 0.3s;
    }

    tbody img: hover {
        transform: scale(1.05);
    }

    a {
        color: #3498db;
        text-decoration:  none;
        font-weight:  500;
        transition: color 0.3s;
    }

    a: hover {
        color: #2980b9;
        text-decoration: underline;
    }

    /* Responsive design */
    @media (max-width: 768px) {
        input[type="text"] {
            width: 100%;
            margin-right: 0;
            margin-bottom: 10px;
        }

        table {
            font-size: 12px;
        }

        thead th,
        tbody td {
            padding: 10px;
        }

        tbody img {
            max-width: 80px;
            max-height: 80px;
        }
        input{
        	margin: 20px;
        }
    }
</style>
</head>
<body>
    <div>
        <form method="POST" action="ExplorarCatalogoController?ruta=buscarProducto">
            <fieldset>
                <legend>Buscar</legend> <br><br>
                <input type="text" name="productoBuscado" id="3">
                <input type="submit" value="buscar">
            </fieldset>

        </form>
    </div>
    <table>
        <thead>
        	<tr>
	            <th>Imagen</th>
	            <th>Nombre</th>
	            <th>Disponibilidad</th>
	            <th>Precio</th>
	            <th>Detalles</th>
            </tr>
        </thead>

        <tbody>
            
            <c:forEach items="${productos}" var="producto">
	            <tr>
	                <td><img src="img/${producto.imagen}" alt=""></td>
	                <td>${producto. nombre}</td>
	                <td>${producto.descripcion}</td>
	                <td>${producto.precio}</td>
	                <td><a href="ProductoController?id=${producto.idProducto }">Ver detalles</a></td>
	            </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>