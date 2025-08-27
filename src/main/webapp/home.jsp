<html>
<head>
<title>Student Application</title>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
        background: linear-gradient(to right, #007bff, #ffffff);

    }
    h2 {
        color: #333;
    }
    .container {
        background: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 2px 6px rgba(0,0,0,0.2);
        width: 400px;
        text-align: center;
    }
    .btn {
        display: inline-block;
        padding: 10px 15px;
        margin: 10px 0;
        background: #007bff;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        text-decoration: none;
        font-size: 14px;
    }
    .btn:hover {
        background: #0056b3;
    }
</style>
</head>
<body>
<div class="container">
    <h2>Student Management System</h2>

    <form action="Studentcontroller" method="get">
        <input type="submit" value="View Students" class="btn">
    </form>

    <form action="addStudents.jsp">
        <input type="submit" value="Add Student" class="btn">
    </form>
</div>
</body>
</html>
