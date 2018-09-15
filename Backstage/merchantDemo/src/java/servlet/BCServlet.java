package java.servlet;

import com.google.gson.Gson;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.BC.BC;
import java.BC.Block;
import java.BC.DealData;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "BCServlet",urlPatterns = {"block"})
public class BCServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();

        String str, wholeStr = "";
        while((str = br.readLine()) != null) {
            wholeStr += str;
        }
        Gson gson=new Gson();
        Block newBlock=gson.fromJson(wholeStr,Block.class);
        BC.getBlock(newBlock);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void init()throws ServletException{
        super.init();
        BC.run();
    }

}
