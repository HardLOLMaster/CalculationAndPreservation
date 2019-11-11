<%-- 
    Document   : changeForm.jsp
    Created on : 10.11.2019, 21:10:32
    Author     : HardLOLMaster
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <style>
        .required {
            background-color: lightcoral;
        }
    </style>
    <body id="id" id-value="<%out.print(request.getParameter("id"));%>">
        <form method="POST">
            <table>
                <tr>
                    <td align="middle">ФИО</td>
                    <td align="middle"><input class="required" id="surname" name="surname" type="text" required value="<%out.print(request.getParameter("surname"));%>"><br><sup>фамилия</sup></td>
                    <td align="middle"><input class="required" id="first_name" name="first_name" type="text" required value="<%out.print(request.getParameter("first_name"));%>"><br><sup>имя</sup></td>
                    <td align="middle"><input id="second_name" name="second_name" type="text" value="<%out.print(request.getParameter("second_name"));%>"><br><sup>отчество</sup></td>
                </tr> 
                <tr>
                    <td><label>Дата рождения <input class="required" id="birthday" name="birthday" type="date" required value="<%out.print(request.getParameter("birthday"));%>"></label></td>
                </tr>
                <tr>
                    <td align="middle">Паспорт</td>
                    <td width="200px" colspan="2"><label>серия <input class="required" id="series" name="series" type="number" required value="<%out.print(request.getParameter("series"));%>"></label></td>
                    <td width="200px" align="left"><label>№ <input class="required" id="number" name="number" type="number" required value="<%out.print(request.getParameter("pas_number"));%>"></label></td>
                </tr>
                <tr>
                    <td colspan="2" align="right"><input id="submit" type="button" value="Сохранить"></td>
                    <td colspan="2" align="left"><button id="cancel">Отменить</button></td>
                </tr>
            </table>
        </form>
        <script>
            document.getElementById('submit').onclick = function () {
                var id = document.getElementById('id').getAttribute('id-value');
                var surname = document.getElementById('surname').value;
                var first_name = document.getElementById('first_name').value;
                var second_name = '';
                second_name = document.getElementById('second_name').value;
                var series = document.getElementById('series').value;
                var number = document.getElementById('number').value;
                var birthday = document.getElementById('birthday').value;
                if (surname !== ''
                        && first_name !== ''
                        && series !== ''
                        && series.length === 4
                        && number.length === 6
                        && birthday !== '') {
                    var xhr = new XMLHttpRequest();
                    var body = "id=" + id
                            + "&surname=" + surname
                            + "&first_name=" + first_name
                            + "&second_name=" + second_name
                            + "&number=" + number
                            + "&series=" + series
                            + "&birthday=" + birthday;
                    xhr.open("POST", "/changePh", false);
                    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                    xhr.send(body);
                    parent.window.opener.setPh(id, surname, first_name, second_name, birthday, series, number);
                    window.close();
                }
            };
            document.getElementById('cancel').onclick = function () {
                window.close();
            };
        </script>
    </body>
</html>
