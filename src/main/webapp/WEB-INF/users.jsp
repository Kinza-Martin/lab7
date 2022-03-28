
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        
        <title>Users Management System</title>
        <style>
            body {
                color: black;
                background: ghostwhite;
                font-family: sans-serif;
                font-size: 14px;
            }
            </style>
       
        <script>
            function  showEditBox(email,firstName,lastName,role,password){
                document.getElementById("editEdit").style.display = "block";
                document.getElementById("editEmailDisplay").innerHTML = "User email: " + email;
                document.getElementById("editEmail").value= email;
                document.getElementById("editFirstName").value= firstName;
                document.getElementById("editLastName").value = lastName;
                document.getElementById("editRole").value = role; 
                document.getElementById("editPassword").value = password; 
            }
            
            function delEditBox(){
                document.getElementById("editUser"); 
            }
            
        </script>
    </head>
    <body>
        <h1>Kinza's Management System</h1>
        <u><h2>${message}</h2></u>
        <form id="addUser" action="user" method="post">
            
            <input type="hidden" name="action" value="addUser">
            <input type="email" name="email" placeholder='Email' required><br>
            <input type="text" name="firstName" placeholder='First Name'required><br>
            <input type="text" name="lastName" placeholder='Last Name'required><br>
            <input type="password" name="password" placeholder='Password'required><br>
            <select name="role"required>
                
                    <option value="1">System admin</option>
                    <option value="2">Regular user</option>
                    <option value="3">Company admin</option>            
            </select> <br>
            <button type="submit">Add User</button>
            
        </form>
        
        <form id="updateUsers" action="user" method="post">
        <h3>Update Users:</h3>    
            <table>
                    <tr>
                        <th>Email</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Role</th>
                        <th>Action</th>

                    </tr>
                
                <c:forEach items="${users}" var="user">
                    <c:if test="${user.isActive()}">
                        <tr>
                            <td><c:out value='${user.email}'/></td>
                            <td><c:out value='${user.firstName}'/></td>
                            <td><c:out value='${user.lastName}'/></td>
                            <td><c:out value='${user.role.name}'/></td>
                            <td>
                                <button  type="button" onclick="showEditBox
                                    ('<c:out value="${user.email}"/>','<c:out value="${user.firstName}"/>',
                                                                               
                                    '<c:out value="${user.lastName}"/>','<c:out value="${user.role.id}"/>',
                                                                               
                                      '<c:out value="${user.password}"/>')"> 
                                Edit
                                </button> 
                                <button type="sumbit" name="delete" value="<c:out value='${user.email}'/>">
                                Delete
                                </button>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>    
            </table>
            
        
        </div>
        
        
    </body>
</html>
