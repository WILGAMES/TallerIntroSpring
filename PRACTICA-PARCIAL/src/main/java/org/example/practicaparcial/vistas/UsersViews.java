package org.example.practicaparcial.vistas;

import org.example.practicaparcial.model.User;

import java.util.List;

public class UsersViews {

    public String listUsers(List<User> users) {

        StringBuilder sb = new StringBuilder();

        sb.append("<table>");
        sb.append("<theader>");
        sb.append("<tr>");
        sb.append("<th>ID</th>");
        sb.append("<th>Name</th>");
        sb.append("<th>Username</th>");
        sb.append("</tr>");
        sb.append("</theader>");
        sb.append("<tbody>");
        for (User user : users) {
            sb.append("<tr>");
            sb.append("<td>"+user.getId()+"</td>");
            sb.append("<td>"+user.getName()+"</td>");
            sb.append("<td>"+user.getUsername()+"</td>");
            sb.append("</tr>");
        }
        sb.append("</tbody>");
        sb.append("</table>");

        return sb.toString();
    }
}
