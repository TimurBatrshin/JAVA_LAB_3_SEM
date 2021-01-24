<#ftl encoding="utf-8">
<#include "base.ftlh">
<#macro title>
        1.Freemarker
        Профиль
    </#macro>
<#macro style>

</#macro>

<#macro content>
<form action="/profile">
    <div>
        <h2>Ваше Имя</h2>
        <p>${user.get().first_name}</p>
    </div>
    <div>
        <h2>Ваша Фамилия</h2>
        <p>${user.get().last_name}</p>
    </div>
    <div>
        <h2>Ваш email</h2>
        <p>${user.get().email}</p>
    </div>
</form>
</#macro>
