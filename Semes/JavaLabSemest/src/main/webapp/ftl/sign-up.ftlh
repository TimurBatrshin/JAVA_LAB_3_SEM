<#ftl  encoding="UTF-8">
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Регистрация</title>
</head>
<body>
<header>
    <form action="/sign-up" method="post">
        <div>
            <input type="text" placeholder="Ваше имя" name="first_name">
        </div>
        <div>
            <input type="text" placeholder="Ваше фамилия" name="last_name">
        </div>
        <div>
            <input type="text" placeholder="email" name="email">
        </div>
        <div>
            <input type="password" placeholder="password" name="password">
        </div>
        <div>
            <input type="password" placeholder="password-repeat" name="password_repeat">
        </div>
        <div>
            <input type="submit" value="Зарегистрироваться">
        </div>
    </form>
</header>
</body>
</html>
