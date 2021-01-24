<#ftl encoding="utf-8">
<#include "base.ftlh">
<#macro title>
    3.FreeMarker
    Корзина покупателя
</#macro>
<#macro content>
    <p>Самые дорогие товары, которые брали наши покупатели, думаю вы удивлены от таких сладких цен</p>
    <#list productsFromCartUser as product></#list>
    ${productsFromCartUser.get().name}<p>купил по цене</p>${productsFromCartUser.get().cost}
    не смог до конца реальховать,не знал как прикреплять стили к .ftlh, сделал за ночь(не красивые, но главное работает;),
    в пакете фронт будет лежать сайт), тут так же ДЗшка на FREEMARKER
</#macro>