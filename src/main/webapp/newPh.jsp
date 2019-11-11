<%-- 
    Document   : newPh
    Created on : 10.11.2019, 14:04:25
    Author     : HardLOLMaster
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Новый страхователь</title>
    </head>
    <style>
        .required {
            background-color: lightcoral;
        }
    </style>
    <body>
        <form>
            <table>
                <tr>
                    <td align="middle">ФИО</td>
                    <td align="middle"><input class="required" id="surname" name="surname" type="text" required><br><sup>фамилия</sup></td>
                    <td align="middle"><input class="required" id="first_name" name="first_name" type="text" required><br><sup>имя</sup></td>
                    <td align="middle"><input id="second_name" name="second_name" type="text"><br><sup>отчество</sup></td>
                </tr> 
                <tr>
                    <td><label>Дата рождения <input class="required" id="birthday" type="date" required></label></td>
                </tr>
                <tr>
                    <td colspan="2" align="right"><input id="submit" type="button" value="Сохранить"></td>
                    <td colspan="2" align="left"><button id="cancel">Отменить</button></td>
                </tr>
            </table>
        </form>
        <script>
            document.getElementById('cancel').onclick = function () {
                window.location.href = "/phForm";
            };
            document.getElementById('submit').onclick = function () {
                var surname = document.getElementById('surname').value;
                var first_name = document.getElementById('first_name').value;
                var second_name = '';
                second_name = document.getElementById('second_name').value;
                var birthday = document.getElementById('birthday').value;
                if (surname !== '' && first_name !== '' && birthday !== '') {
                    var xhr = new XMLHttpRequest();
                    var body = "surname=" + surname
                            + "&first_name=" + first_name
                            + "&second_name=" + second_name
                            + "&birthday=" + birthday;
                    xhr.open("POST", "/newPh", false);
                    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                    xhr.send(body);
                    //parent.window.opener.setFioAndDate(surname, first_name, second_name, birthday);
                    window.location.href = "/phForm";
                }
            }
        </script>
    </body>
</html>
