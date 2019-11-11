x<%-- 
    Document   : calculated
    Created on : 09.11.2019, 0:37:03
    Author     : HardLOLMaster
--%>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.time.LocalDate"%>
<%@page import="com.mycompany.calculationandpreservation.database.entities.Address"%>
<%@page import="com.mycompany.calculationandpreservation.database.entities.PolicyHolder"%>
<%@page import="com.mycompany.calculationandpreservation.database.entities.Contract"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<form>
    <fieldset>
        <legend>Рассчет</legend>
        <table>
            <tr>
                <td colspan="2" align="right" class="calculation">
                    <label>Страховая сумма 
                        <input id="sum" type="number" required 
                               value="<%out.print(request.getAttribute("sum"));%>"></label>
                </td>
                <td class="calculation">
                    <label>Срок действия с 
                        <input id="dateFrom" type="date" required 
                               value="<%out.print(request.getAttribute("dateFrom"));%>"></label>
                </td>
                <td class="calculation">
                    <label>Срок действия по 
                        <input id="dateTo" type="date" required 
                               value="<%out.print(request.getAttribute("dateTo"));%>"></label>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="right" class="calculation">
                    <label>Тип недвижимости 
                        <select id="property" required 
                                value="<%out.print(request.getAttribute("property"));%>">
                            <option value="apartament">Квартира</option>
                            <option value="house">Дом</option>
                            <option value="room">Комната</option>
                        </select>
                    </label>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="right" class="calculation">
                    <label>Год постройки 
                        <select required id="year" 
                                value="<%out.print(request.getAttribute("year"));%>">
                            <%
                                for (int i = 1600; i <= LocalDate.now().getYear(); i++) {
                                    out.print("<option selected value=\"" + i + "\">" + i + "</option>");
                                }
                            %>
                        </select>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="right" class="calculation">
                    <label>Площадь, кв.м. <input id="area" type="number" step="0.1" required 
                                                 value="<%out.print(request.getAttribute("area"));%>"></label>
                </td>
            </tr>
            <tr>
                <td class="calculation">
                </td>
                <td colspan="2" align="middle" class="calculation">
                    <button id="send">Рассчитать</button>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="right" class="calculation">
                    <label>Дата расчета <input id="date" type="date" readonly 
                                               value="<%out.print(request.getAttribute("conclusion_date"));%>"></label>
                </td>
                <td colspan="2" align="middle" class="calculation">
                    <label>Премия <input id="prize" type="number" step="0.01" 
                                         value="<%out.print(request.getAttribute("prize"));%>"></label>
                </td>
            </tr>
        </table>
    </fieldset>
</form>
<script>
    var sendCalcForm = document.getElementById('send');
    var calcForm = document.getElementById('calc');
    sendCalcForm.onclick = function () {
        var body = "";
        body = body + "sum=" + document.getElementById('sum').value;
        body = body + "&dateFrom=" + document.getElementById('dateFrom').value;
        body = body + "&dateTo=" + document.getElementById('dateTo').value;
        body = body + "&property=" + document.getElementById('property').value;
        body = body + "&year=" + document.getElementById('year').value;
        body = body + "&area=" + document.getElementById('area').value;
        var xhr = new XMLHttpRequest();
        xhr.open('POST', '/calculate', false);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.send(body);
        calcForm.innerHTML = xhr.responseText;
    }
</script>
