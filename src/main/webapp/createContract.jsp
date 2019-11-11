<%-- 
    Document   : createContract
    Created on : 09.11.2019, 17:11:26
    Author     : HardLOLMaster
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.mycompany.calculationandpreservation.database.entities.PolicyHolder"%>
<%@page import="com.mycompany.calculationandpreservation.database.entities.Address"%>
<%@page import="com.mycompany.calculationandpreservation.database.entities.Contract"%>
<%@page import="java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<style>
    .calculation {
        width: 300px;
    }
    .policyholder {
        width: 300px;
    }
    input {
        width : 150px;
    }
    fieldset {
        width: 930px;
    }
    select {
        width: 150px;   
    }
    .fieldsetWithoutBorders {
        border:0 none;
    }
    #fio {
        width: 400px;
    }
    .small-input {
        width: 50px;
    }
    .required {
        background-color: lightcoral;
    }
</style>
<%
    Contract c = (Contract) request.getAttribute("contract");
    Address address = null;
    PolicyHolder ph = null;
    boolean isNull = (c == null);
    String dateFrom = LocalDate.now().toString();
    String dateTo = "";
    String calculationDate = "";
    if (!isNull) {
        address = c.getProperty();
        ph = c.getPolicyholder();
        Date df = c.getDateFrom();
        Date dt = c.getDateTo();
        dateFrom = "" + (df.getYear() + 1900) + "-" + (df.getMonth() + 1) + "-";
        dateFrom += (df.getDate() < 10) ? "" + 0 + df.getDate() : "" + df.getDate();
        dateTo = "" + (dt.getYear() + 1900) + "-" + (dt.getMonth() + 1) + '-';
        dateTo += (dt.getDate() < 10) ? "" + 0 + dt.getDate() : "" + dt.getDate();
        calculationDate = LocalDate.now().toString();
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contract</title>
    </head>
    <body>
        <form id="calc" method="POST">
            <fieldset>
                <legend>Рассчет</legend>
                <table>
                    <tr>
                        <td colspan="2" align="right" class="calculation">
                            <label>Страховая сумма 
                                <input class="required" name="sum" id="sum" type="number" required 
                                       value="<%if (isNull) {
                                               out.print("");
                                           } else {
                                               out.print(c.getSum());
                                           }%>"></label>
                        </td>
                        <td class="calculation">
                            <label>Срок действия с 
                                <input class="required" name="dateFrom" id="dateFrom" type="date" required 
                                       value="<%out.print(dateFrom);%>"></label>
                        </td>
                        <td class="calculation">
                            <label>Срок действия по 
                                <input class="required" name="dateTo" id="dateTo" type="date" required 
                                       value="<%out.print(dateTo);%>"></label>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="right" class="calculation">
                            <label>Тип недвижимости 
                                <select class="required" name="property" id="property" required 
                                        value="<%if (isNull) {
                                                out.print("");
                                            } else {
                                                out.print(c.getProperty().getBuildingType());
                                            }%>">
                                    <%
                                        if (isNull) {
                                            out.println("<option value=\"apartament\">Квартира</option>");
                                            out.println("<option value=\"house\">Дом</option>");
                                            out.println("<option value=\"room\">Комната</option>");
                                        } else {
                                            if (c.getProperty().getBuildingType().equals("apartament")) {
                                                out.println("<option selected value=\"apartament\">Квартира</option>");
                                            } else {
                                                out.println("<option value=\"apartament\">Квартира</option>");
                                            }
                                            if (c.getProperty().getBuildingType().equals("house")) {
                                                out.println("<option selected value=\"house\">Дом</option>");
                                            } else {
                                                out.println("<option value=\"house\">Дом</option>");
                                            }
                                            if (c.getProperty().getBuildingType().equals("room")) {
                                                out.println("<option selected value=\"room\">Комната</option>");
                                            } else {
                                                out.println("<option value=\"room\">Комната</option>");
                                            }
                                        }
                                    %>
                                </select>
                            </label>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="right" class="calculation">
                            <label>Год постройки 
                                <select class="required" name="year" required id="year">
                                    <%
                                        for (int i = 1600; i <= LocalDate.now().getYear(); i++) {
                                            if (isNull) {
                                                out.print("<option selected value=\"" + i + "\">" + i + "</option>");
                                            } else if (i == c.getProperty().getBuildingYear()) {
                                                out.print("<option selected value=\"" + i + "\">" + i + "</option>");
                                            } else {
                                                out.print("<option value=\"" + i + "\">" + i + "</option>");
                                            }
                                        }
                                    %>
                                </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="right" class="calculation">
                            <label>Площадь, кв.м. <input class="required" name="area" id="area" type="number" step="0.1" required 
                                                         value="<%if (isNull) {
                                                                 out.print("");
                                                             } else {
                                                                 out.print(c.getProperty().getBuildingArea());
                                                             }%>"></label>
                        </td>
                    </tr>
                    <tr>
                        <td class="calculation">
                        </td>
                        <td colspan="2" align="middle" class="calculation">
                            <input type="submit" id="send" value="Рассчитать">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="right" class="calculation">
                            <label>Дата расчета <input class="required" name="date" id="date" type="date" readonly 
                                                       value="<%out.print(calculationDate);%>"></label>
                        </td>
                        <td colspan="2" align="middle" class="calculation">
                            <label>Премия <input class="required" name="prize" id="prize" type="number" step="0.01" 
                                                 value="<%
                                                     if (isNull) {
                                                         out.print("");
                                                     } else if (c.getPrize() < 0) {
                                                         out.print("");
                                                     } else {
                                                         float prize = c.getPrize();
                                                         prize *= 100;
                                                         int result = (int) Math.round(prize);
                                                         out.print((float) result / 100);
                                                     }
                                                 %>"></label>
                        </td>
                    </tr>
                </table>
            </fieldset>
        </form>
        <div id="contract_basic">
            <table>
                <tr>
                    <td>
                        <label>№ договора <input class="required" name="number" id="number" type="number" pattern="[0-9]{6}" required></label>
                    </td>
                    <td>
                        <label>Дата заключения <input class="required" id="conclusion_date" name="conclusion_date" type="date" readonly required
                                                      value="<%out.print(calculationDate);%>"></label>
                    </td>
                </tr>
            </table>
        </div>
        <fieldset class="">
            <legend><strong>Страхователь</strong></legend>
            <div id="policyholder">
                <table border="0px" width="800px">
                    <tr>
                        <td align="middle" class="policyholder"><input type="button" id="choosePh" method="POST" action="/policyholder" value="Выбрать"></td>
                        <td>ФИО</td>
                        <td align="right" colspan="3"><input class="required"  name="fio" id="fio" type="text" readonly></td>
                        <td align="middle" class="policyholder"><button id="change">Изменить</button></td>
                    </tr>
                    <tr>
                        <td colspan="3" class="policyholder"><label>Дата рождения <input class="required" id="birthday" type="date" readonly></label></td>
                        <td class="policyholder" align="middle">Паспорт</td>
                        <td class="policyholder"><label>серия<br><input class="required" id="series" type="text" maxlength="4" readonly></label></td>
                        <td class="policyholder"><label>№<br><input class="required" id="pas_number" type="text" maxlength="6" readonly></label></td>
                    </tr>
                </table>
            </div>
        </fieldset>
        <script>
            document.getElementById('change').onclick = function () {
                console.log("Change click");
                if (document.getElementById('fio').value !== '') {
                    var id = document.getElementById('policyholder').getAttribute('id-value');
                    var surname = document.getElementById('fio').getAttribute('surname');
                    var first_name = document.getElementById('fio').getAttribute('first_name');
                    var second_name = document.getElementById('fio').getAttribute('second_name');
                    var series = document.getElementById('series').value;
                    var pas_number = document.getElementById('pas_number').value;
                    var birthday = document.getElementById('birthday').value;
                    var request = "?id=" + id
                            + "&surname=" + surname
                            + "&first_name=" + first_name
                            + "&second_name=" + second_name
                            + "&series=" + series
                            + "&pas_number=" + pas_number
                            + "&birthday=" + birthday;
                    window.open("/changePh" + request, "ChangePh", 'width=850,height=400,location=no,menubar=no,toolbar=no');
                }
            };
            function setPh(id, surname, first_name, second_name, birthday, series, number) {
                document.getElementById('policyholder').setAttribute('id-value', id);
                document.getElementById('fio').value = surname + ' ' + first_name + ' ' + second_name;
                document.getElementById('fio').setAttribute('surname', surname);
                document.getElementById('fio').setAttribute('first_name', first_name);
                document.getElementById('fio').setAttribute('second_name', second_name);
                document.getElementById('birthday').value = birthday;
                document.getElementById('series').value = series;
                document.getElementById('pas_number').value = number;
            }
            function setFioAndDate(surname, first_name, second_name, birthday) {
                document.getElementById('fio').value = surname + ' ' + first_name + ' ' + second_name;
                document.getElementById('birthday').value = birthday;
            }
            ;
            document.getElementById('choosePh').onclick = function () {
                window.open('/phForm', 'PolicyHolder', 'width=850,height=400,location=no,menubar=no,toolbar=no');
            };
        </script>
        <div>
            <form id="property">
                <fieldset>
                    <legend><strong>Адрес недвижимости</strong></legend>
                    <table>
                        <tr>
                            <td align="middle"><input class="required" id="country" type="text" required><br><span><sup>государство</sup></span></td>
                            <td align="middle"><input id="index" type="text"><br><span><sup>индекс</sup></span></td>
                            <td align="middle" colspan="2"><input class="required" id="country_state" type="text" required><br><span><sup>республика, край, область</sup></span></td>
                            <td align="middle" colspan="2"><input id="region" type="text"><br><span><sup>район</sup></span></td>
                        </tr>
                        <tr>
                            <td align="middle"><input class="required" id="city" type="text" required><br><span><sup>населенный пункт</sup></span></td>
                            <td align="middle" colspan="3"><input class="required" id="street" type="text" required><br><span><sup>улица</sup></span></td>
                            <td align="middle" class="small-input"><input id="building" type="number"><br><span><sup>дом</sup></span></td>
                            <td align="middle" class="small-input"><input id="corpus" type="text"><br><span><sup>корпус</sup></span></td>
                            <td align="middle" class="small-input"><input id="building_structure" type="text"><br><span><sup>строение</sup></span></td>
                            <td align="middle" class="small-input"><input class="required" id="apartament" type="number" required><br><span><sup>квартира</sup></span></td>
                        </tr>
                    </table>
                </fieldset>
                <table>
                    <tr>
                        <td><input id="save" type="button" value="Сохранить"></td>
                        <td><input id="contracts" type="button" value="К списку договоров"></td>
                    </tr>
                </table>
            </form>
            <script>
                document.getElementById('save').onclick = function () {
                    /*********************************************************/
                    /*********************************************************/
                    /*********************************************************/
                    //PROPERTY INFO
                    var country = document.getElementById('country').value;//
                    var index = document.getElementById('index').value;
                    var country_state = document.getElementById('country_state').value;//
                    var region = document.getElementById('region').value;
                    var city = document.getElementById('city').value;//
                    var street = document.getElementById('street').value;//
                    var building = document.getElementById('building').value;
                    var corpus = document.getElementById('corpus').value;
                    var building_structure = document.getElementById('building_structure').value;
                    var apartament = document.getElementById('apartament').value;//
                    //CONTRACT INFO
                    var prize = document.getElementById('prize').value;//
                    var date = document.getElementById('date').value;//
                    var area = document.getElementById('area').value;//
                    var year = document.getElementById('year').value;//
                    var dateFrom = document.getElementById('dateFrom').value;//
                    var dateTo = document.getElementById('dateTo').value;//
                    var sum = document.getElementById('sum').value;//
                    var property = document.getElementById('property').value;//
                    //CONTRACT BASIC
                    var number = document.getElementById('number').value;//
                    var conclusion_date = document.getElementById('conclusion_date').value;//
                    //POLICY HOLDER
                    var id = document.getElementById('policyholder').getAttribute('id-value');//
                    var surname = document.getElementById('fio').getAttribute('surname');//
                    var first_name = document.getElementById('fio').getAttribute('first_name');//
                    var second_name = document.getElementById('fio').getAttribute('second_name');
                    var series = document.getElementById('series').value;//
                    var pas_number = document.getElementById('pas_number').value;//
                    var birthday = document.getElementById('birthday').value;//
                    /*********************************************************/
                    /*********************************************************/
                    /*********************************************************/
                    if (country !== ''
                            && country_state !== ''
                            && city !== ''
                            && street !== ''
                            && apartament !== 0
                            && prize !== 0
                            && date !== ''
                            && area > 0
                            && dateFrom !== ''
                            && dateTo !== ''
                            && sum > 0
                            && number.length === 6
                            && conclusion_date !== ''
                            && surname !== ''
                            && first_name !== ''
                            && series.length === 4
                            && pas_number.length === 6
                            && birthday !== '') {
                        var body = "country=" + country
                                + "&index=" + index
                                + "&country_state=" + country_state
                                + "&region=" + region
                                + "&city=" + city
                                + "&street=" + street
                                + "&building=" + building
                                + "&corpus=" + corpus
                                + "&building_structure=" + building_structure
                                + "&apartament=" + apartament
                                + "&prize=" + prize
                                + "&date=" + date
                                + "&area=" + area
                                + "&year=" + year
                                + "&dateFrom=" + dateFrom
                                + "&dateTo=" + dateTo
                                + "&sum=" + sum
                                + "&property=" + property
                                + "&pas_number=" + pas_number
                                + "&series=" + series
                                + "&number=" + number
                                + "&conclusion_date=" + conclusion_date
                                + "&policeholderId=" + id;
                        /*var xhr = new XMLHttpRequest();
                         xhr.open("POST","/saveContract",false);
                         xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                         xhr.send(body);*/
                        window.location.href = "/saveContract?" + body;
                    }
                };
            </script>
            <script>
                document.getElementById('contracts').onclick = function () {
                    window.location.href = '/';
                };
            </script>
        </div>
    </body>
</html>
