<%-- 
   Document   : choosePhForm
   Created on : 09.11.2019, 21:52:34
   Author     : HardLOLMaster
--%>

<%@page import="java.util.List"%>
<%@page import="com.mycompany.calculationandpreservation.database.entities.PolicyHolder"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<PolicyHolder> phList = (List<PolicyHolder>) request.getAttribute("policyholders");
%>
<html>
    <head>
        <title>Страхователь</title>
    </head>
    <style>
        .required {
            background-color: lightcoral;
        }
    </style>
    <body>
        <form method="POST" method="/phForm">
            <table>
                <tr>
                    <td>ФИО</td>
                    <td align="middle"><input class="required" name="surname" id="surname" type="text" required><br><sup>фамилия</sup></td>
                    <td align="middle"><input class="required" name="first_name" id="first_name" type="text" required><br><sup>имя</sup></td>
                    <td align="middle"><input name="second_name" id="second_name" type="text"><br><sup>отчество</sup></td>
                    <td align="middle"><input type="submit" id="find" value="Искать"></td>
                </tr>
            </table>
        </form>
        <div height="250px">
            <table id="ph" data-value="<%
                if (phList != null) {
                    out.print(phList.size());
                }%>">
                <tr>
                    <td width="250px">ФИО</td>
                    <td width="225px">Дата рождения</td>
                    <td width="225pxpx">Паспортные данные</td>
                </tr>
                <%
                    if (phList != null) {
                        int i = 1;
                        for (PolicyHolder ph : phList) {
                            String birthday = "";
                            String birthdayFormat = "";
                            String day = "";
                            String month = "";
                            String year = "" + (ph.getBirthday().getYear() + 1900);
                            if (ph.getBirthday().getDate() < 10) {
                                day = "0" + ph.getBirthday().getDate();
                            } else {
                                day = ph.getBirthday().getDate() + "";
                            }
                            if ((ph.getBirthday().getMonth() + 1) < 10) {
                                month = "0" + (ph.getBirthday().getMonth() + 1);
                            } else {
                                month = "" + (ph.getBirthday().getMonth() + 1);
                            }
                            birthday = year + '-' + month + '-' + day;
                            birthdayFormat = day + '.' + month + '.' + year;
                            out.println("<tr "
                                    + "id=\"row" + i
                                    + "\" passport-number=\"" + ph.getPassport_number() + "\""
                                    + " passport-series=\"" + ph.getPassport_series() + "\""
                                    + " surname=\"" + ph.getSurname() + "\""
                                    + " first-name=\"" + ph.getFirst_name() + "\""
                                    + " second-name=\"" + ph.getSecond_name() + "\""
                                    + " birthday=\"" + birthday + "\""
                                    + " id-value=\"" + ph.getId() + "\""
                                    + " tabindex=\"-1\"><td>"
                                    + ph.getSurname() + ' ' + ph.getFirst_name() + ' ' + ph.getSecond_name()
                                    + "</td>");
                            out.println("<td>"
                                    + birthdayFormat
                                    + "</td>");
                            out.println("<td>"
                                    + ph.getPassport_series() + ' ' + ph.getPassport_number()
                                    + "</td></tr>");
                            i++;
                        }
                    }
                %>
            </table></div>
        <table>
            <tr>
                <td width="233px" align="right">
                    <button id="choose">Выбрать</button>
                </td>
                <td width="10px" align="middle">
                    <button id="new">Новый</button>
                </td>
                <td width="233px" align="left">
                    <button id="close">закрыть</button>
                </td>
            </tr></table>
        <script>
            var trAr = [];
            var ph = document.getElementById('ph');
            for (let a = 1; a <= ph.getAttribute('data-value'); a++) {
                trAr.push(document.getElementById("row" + a));
            }
            for (let a = 0; a < ph.getAttribute('data-value'); a++) {
                trAr[a].onfocus = function () {
                    var focusObject = trAr[a];
                    document.getElementById('choose').onclick = function () {
                        window.opener.setPh(focusObject.getAttribute('id-value'),
                                focusObject.getAttribute('surname'),
                                focusObject.getAttribute('first-name'),
                                focusObject.getAttribute('second-name'),
                                focusObject.getAttribute('birthday'),
                                focusObject.getAttribute('passport-series'),
                                focusObject.getAttribute('passport-number'));
                        window.close();
                    };
                };
            }
            function setFioAndDate(surname, first_name, second_name, birthday) {
                window.opener.setFioAndDate(surname, first_name, second_name, birthday);
                window.close();
            }
            document.getElementById('close').onclick = function () {
                window.close();
            };
            document.getElementById('new').onclick = function () {
                window.location.href = '/newPh';
            };
        </script>
    </body>
</html>