<#ftl encoding="utf-8">
<#include "base.ftlh">
<#macro title>
    2.Freemarker
    Наши продукты по самым дешевым ценам
</#macro>
<#macro style>

</#macro>

<#macro content>
    <#list products as product>
        <th>
            <tr>${product.name}</tr>
            <tr>${product.cost}</tr>
        </th>
        <th>
            <tr></tr>
            <tr></tr>
        </th>
    </#list>
</#macro>