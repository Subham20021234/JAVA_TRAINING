<html>
<head>
<title>Add Student</title>
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
    .form-container {
        background: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 2px 6px rgba(0,0,0,0.2);
        width: 400px;
        text-align: center;
    }
    h2 { 
        color: #333; 
        margin-bottom: 15px;
    }
    label { 
        display: block; 
        margin-top: 10px; 
        font-weight: bold; 
        text-align: left;
    }
    input[type=text] {
        width: 100%;
        padding: 8px;
        margin-top: 5px;
        border: 1px solid #ccc;
        border-radius: 5px;
    }
    .btn {
        margin-top: 15px;
        padding: 10px 15px;
        background: #28a745;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        width: 100%;
        font-size: 14px;
    }
    .btn:hover { background: #1e7e34; }

    .back-btn {
        margin-top: 10px;
        padding: 10px 15px;
        background: #007bff;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
         font-size: 14px;
        text-decoration: none;
        display: inline-block;
    width: 95%; 
    }
    .back-btn:hover { background: #0056b3; }
</style>
</head>
<body>
<div class="form-container">
    <h2>Add New Student</h2>
    <form action="Addstudent" method="post">
        <label>Roll No:</label>
        <input type="text" name="roll" required>

        <label>Name:</label>
        <input type="text" name="name" required>

        <label>Class:</label>
        <input type="text" name="className" required>

        <input type="submit" value="Save Student" class="btn">
    </form>

     <a href="home.jsp" class="back-btn">Back to Home</a>
</div>
</body>
</html>
