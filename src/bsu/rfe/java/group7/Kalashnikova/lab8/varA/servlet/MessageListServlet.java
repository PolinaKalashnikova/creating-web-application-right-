package bsu.rfe.java.group7.Kalashnikova.lab8.varA.servlet;

import javax.servlet.ServletException;
import bsu.rfe.java.group7.Kalashnikova.lab8.varA.entity.ChatMessage;
import bsu.rfe.java.group7.Kalashnikova.lab8.varA.entity.ChatUser;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;



public class MessageListServlet extends ChatServlet {
    private static final long serialVersionUID = 1L;
    private String prevAuthor = "";
    private String spaces = "";

    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
// Установить кодировку HTTP-ответа UTF-8
        response.setCharacterEncoding("utf8");
// Получить доступ к потоку вывода HTTP-ответа
        PrintWriter pw = response.getWriter();
// Записть в поток HTML-разметку страницы
        pw.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'/><meta http-equiv='refresh' content='10'></head>");
        pw.println("<body>");
// В обратном порядке записать в поток HTML-разметку для каждого сообщения
        for (int i=messages.size()-1; i>=0; i--) {
            ChatMessage aMessage = messages.get(i);
            if (prevAuthor != aMessage.getAuthor().getName()){prevAuthor="";}
            if (prevAuthor == "") {
                pw.println("<div><strong>" + "<pre>" + aMessage.getAuthor().getName()
                        + "</strong>: " + aMessage.getMessage() + "</pre>" + "</div>");
                prevAuthor = aMessage.getAuthor().getName();
            }else {
                for (int j = 0; j <= prevAuthor.length() + 1; j++) {
                    spaces = spaces + " ";
                }
                pw.println("<div><strong>"
                        + "</strong> " + "<pre>" + spaces + aMessage.getMessage() + "</pre>" + "</div>");
                if (prevAuthor != aMessage.getAuthor().getName()) {
                    prevAuthor = "";
                }
                spaces = "";
            }
        }
        prevAuthor = "";
        pw.println("</body></html>");
    }
}