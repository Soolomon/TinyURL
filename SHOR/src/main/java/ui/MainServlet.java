package ui;

import db.UrlManip;
import logic.AlgoReduction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/")
public class MainServlet extends HttpServlet  {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        AlgoReduction url=new AlgoReduction(req.getParameter("shorten_url"));
        req.setAttribute("tinylink", req.getRequestURL() + url.doShort());
        req.getRequestDispatcher("mypage.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String[] suburl = req.getRequestURI().split("/"); // поделить ссылку на субсылки
        UrlManip tinyurl = new UrlManip();
        if (suburl.length != 0) { // если в ссылке имеются символы после слеша
            if (tinyurl.getLongurl(suburl[1])==null)// тогда проверить есть ли в наличии длинная строка по (ключу) короткой ссылке
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);// если нету отправить ошыбку
            else { // если есть тогда
                req.setAttribute("longurl", tinyurl.getLongurl(suburl[1]));//взять длинную строку по короткой, и установить значения переменной
                req.getRequestDispatcher("away.jsp").forward(req, resp); // и отправить на страницу переадресации
            }
        } else // если  входящая ссылка  пустая , тогда отправить на главную страницу

            req.getRequestDispatcher("mypage.jsp").forward(req, resp);
    }



}
