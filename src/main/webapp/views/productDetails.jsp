<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<title>Coffee Shop</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	
</head>
<body>
  <div class='container'>
    <h1>Coffee Shop</h1>
    <div class='navbar navbar-inverse'>
      <div class='navbar-inner nav-collapse' >
        <ul class="nav navbar-nav" style="width: 100%">
          <li class="active"><a href="admin">Home</a></li>
          <sec:authorize access="hasRole('ROLE_USER')">
          <li><a href="order">Order</a></li>
           </sec:authorize>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
          <li><a href="editProducts">Edit Products</a></li>
        
          <li><a href="persons">Manage Person</a></li>
           <li><a href="orders" >List Orders</a></li>
             </sec:authorize>
             
            <li class="pull-right"><a href="logout" >Logout</a></li>
             <li class="pull-right"><a href="editProfile/${user.id}" >${user.firstName }  </a></li>
        </ul>
      </div>
    </div>
    <div id='content' class='row-fluid'>
    <div class='col-md-2 sidebar'>
         <h3>Today's Special<h3>
        <ul class="nav nav-tabs nav-stacked">
          <li><a href='#'>Latte</a></li>
        </ul>
      </div>
      <div class='col-md-8 main'>
      <div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-body">
                    <h5 class="text-center">
                        New Product</h5>
                    <form class="form form-signup" role="form" method="POST" 

action="${contextPath}/products/${product.id }" >
                    <div class="form-group">
                        <span>${message}</span>
                        <div class="input-group">
                             <span class="input-group-addon">Product Name</span>
                            
                            <input type="text" name="productName" 

value="${product.productName }"  class="form-control"  autofocus="true" />
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                             <span class="input-group-addon">Price</span>
                            <input type="text" name="price" value="${product.price }" 

class="form-control"  />
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                         <span class="input-group-addon">Type</span>
                          <select name="productType" class="form-control">
                          <c:forEach items="${productType}" var="type">
                          <option  <c:if 

test="${type==product.productType}">selected</c:if> value="${type}">${type}</option>
                          </c:forEach>
                          </select>
                          
                   
                  <span>    <c:if test="${param.error ne null}"></c:if>
                        </div>
                    </div>
                     <div class="form-group">
                        <div class="input-group">
                             <span class="input-group-addon">Description</span>
                            <input type="text" name="description" 

value="${product.description }" class="form-control"  />
                        </div>
                    </div>
                </div>
                 <input type="hidden" name="${_csrf.parameterName}" 

value="${_csrf.token}"/>
               <button class="btn btn-sm btn-primary btn-block" type="submit">
                    Save</button>
                    
                    </form>
            </div>
        </div>
    </div>
</div>

  
       
       
      </div>
     
    </div>
  </div>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
 
</body>
</html>