package com.example.hello;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class ResponseServlet extends HttpServlet {
    private static Logger log = Logger.getLogger(ResponseServlet.class.getName());

    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException{
/* TODO#1 buffer Size 설정
        1024 byte = 1kb
         - 1KB 이하의 작은 양의 데이터 전송 시 출력 buffer가 꽉 차지 않아도 바로 전송.
         - 1KB 이상의 큰 데이터양을 전송할 때는 출력 버퍼가 가득 차기 전까지 데이터를 쌓은 다음에 한 번에 전송.
         - default bufferSize : 8192 byte = 8KB

     */

        log.info("default buffer size : {" + response.getBufferSize() + "}");
        response.setBufferSize(1024);

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        try(PrintWriter out = response.getWriter()){

            out.println("locale=" + request.getLocale());
            out.println("parameter name=" + request.getParameter("name"));
            // TODO#2 flush 버퍼링 된 출력 바이트를 즉시 쓰도록(소켓을 통해서 내보냄) 강제함. clinet와 연결이 종료됨. 즉.. 아래 로직은 실행되더라도.. 브라우저에 표시 안됨..
            //out.flush();
            //out.close();

            String userId = request.getParameter("userId");
            log.info("userId:{" + userId + "}");
            if (userId == null || userId.isEmpty()) {
                // TODO#3 response를 초기화.
                response.reset();

                // TODO#4 error 코드를 500 설정함
                response.setStatus(500);
                // TODO #5  error 코드를 500으로 설정하고 error message  'name is empty' 설정함.
                response.sendError(500, "name is empty");
                return;
            }

            //TODO#6 redirect
            String redirect = request.getParameter("redirect");
            if(Objects.nonNull(redirect)){
                response.sendRedirect(redirect);
                return;
            }

            out.println("method=" + request.getMethod());
            out.println("request uri=" + request.getRequestURI());

            //TODO#7 reset buffer - response 객체에 담겨있던 모든 buffer 초기화
            response.resetBuffer();

            out.println("User-Agent header=" + request.getHeader("User-Agent"));

        }catch (Exception e){
            log.warning("/req : {" + e.getMessage() + "}" + e);
        }
    }
}
