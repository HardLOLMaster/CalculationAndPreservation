<%-- 
    Document   : contracts
    Created on : 02.11.2019, 16:29:30
    Author     : HardLOLMaster
--%>

<%@page import="com.mycompany.calculationandpreservation.database.entities.Address"%>
<%@page import="com.mycompany.calculationandpreservation.database.entities.PolicyHolder"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.calculationandpreservation.database.entities.Contract"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Calculation and preservation</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <button id="create">
            Создать договор
        </button>
        <button id="open">
            Открыть договор
        </button>
        <%
            List<Contract> contracts = (List<Contract>) request.getAttribute("contracts");
            long i = 0;
        %>
        <table id="contracts" data-value="<%out.print(contracts.size());%>">
            <tr backgroud-color="light-gray">
                <th>Серия-Номер</th>
                <th>Дата заключения</th>
                <th>Страхователь</th>
                <th>Премия</th>
                <th>Срок действия</th>
            </tr>
            <%
                if (contracts != null) {
                    for (Contract c : contracts) {
                        PolicyHolder ph = c.getPolicyholder();
                        i++;
            %>
            <%
                /*Создание строк с датой для вывода на страницу*/
                String date = "";
                if (c.getDateFrom().getDate() < 10) {
                    date += "" + '0' + c.getDateFrom().getDate() + '.';
                } else {
                    date += "" + c.getDateFrom().getDate() + '.';
                }
                if ((c.getDateFrom().getMonth()+1) < 10) {
                    date += "" + '0' + (c.getDateFrom().getMonth()+1) + '.';
                } else {
                    date += "" + (c.getDateFrom().getMonth()+1) + '.';
                }
                date += "" + (c.getDateFrom().getYear() + 1900) + '-';
                if (c.getDateTo().getDate() < 10) {
                    date += "" + '0' + c.getDateTo().getDate() + '.';
                } else {
                    date += "" + c.getDateTo().getDate() + '.';
                }
                if ((c.getDateTo().getMonth()+1) < 10) {
                    date += "" + '0' + (c.getDateTo().getMonth()+1) + '.';
                } else {
                    date += "" + (c.getDateTo().getMonth()+1) + '.';
                }
                date += "" + (c.getDateTo().getYear() + 1900);
                
                String concDate = "";
                if (c.getConclusion_date().getDate() < 10) {
                    concDate += "" + '0' + c.getConclusion_date().getDate() + '.';
                } else {
                    concDate += "" + c.getConclusion_date().getDate() + '.';
                }
                if ((c.getConclusion_date().getMonth()+1) < 10) {
                    concDate += "" + '0' + (c.getConclusion_date().getMonth()+1) + '.';
                } else {
                    concDate += "" + (c.getConclusion_date().getMonth()+1) + '.';
                }
                concDate += "" + (c.getConclusion_date().getYear() + 1900);
            %>
            <%
                        out.println(
                                "<tr id=\"row" + i + "\" "
                                + "tabindex=\"-1\" data-value=\"" + c.getId() + "\">"
                                + "<td id=\"contract_number"
                                + i + "\">" + c.getContract_number() + "</td>"
                                + "<td id=\"conclusion_date"
                                + i + "\">" + concDate + "</td>"
                                + "<td id=\"fio" + i + "\">"
                                + ph.getSurname()
                                + ' ' + ph.getFirst_name()
                                + ' ' + ph.getSecond_name() + "</td>"
                                + "<td id=\"prize" + i
                                + "\">" + c.getPrize() + "</td>"
                                + "<td id=\"duration" + i + "\">"
                                + date + "</td>"
                                + "</tr>"
                        );
                    }
                } else {
                    out.println(
                            "<tr>"
                            + "<td>Нет записей</td>"
                            + "<td>Нет записей</td>"
                            + "<td>Нет записей</td>"
                            + "<td>Нет записей</td>"
                            + "<td>Нет записей</td>"
                            + "</tr>"
                    );
                }
            %>
        </table>
    </body>
    <script>
        var trAr = [];
        var create = document.getElementById('create');
        var open = document.getElementById('open');
        var contracts = document.getElementById('contracts');
        for (let a = 1; a <= contracts.getAttribute('data-value'); a++) {
            trAr.push(document.getElementById("row" + a));
        }
        for (let a = 0; a < contracts.getAttribute('data-value'); a++) {
            trAr[a].onfocus = function () {
                var focusObject = trAr[a];
                open.onclick = function () {
                    window.location.href = '/openContract?id='+focusObject.getAttribute('data-value');
                };
                addEventListener("dblclick", function () {
                    window.location.href = '/openContract?id='+focusObject.getAttribute('data-value');
                });
            };
        }
        create.onclick = function() {
            window.location.href = '/createContract';    
        };
    </script>
</html>
