<#ftl encoding="UTF-8"/>
<!doctype html>
<html lang="en">
<head>
</head>
<body>
<form method="post" action="/users">
    <input type="text" name="first_name">
    <input type="text" name="last_name">
    <input type="submit" value="Add">
</form>
<table>
    <tr>
        <th>Firstname</th>
        <th>Lastname</th>
    </tr>
    <#list users as user>
        <tr>
            <td>${user.first_name}</td>
            <td>${user.last_name}</td>
        </tr>
    </#list>

</table>
</body>
</html>