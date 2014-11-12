package ro.z2h;

import org.codehaus.jackson.map.ObjectMapper;
import ro.z2h.annotation.MyController;
import ro.z2h.annotation.MyRequestMethod;
import ro.z2h.controller.DepartmentController;
import ro.z2h.controller.EmployeeController;
import ro.z2h.fmk.AnnotationScanUtils;
import ro.z2h.fmk.MethodAttributes;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by hangan on 11/11/2014.
 */
public class MyDispatcherServlet extends HttpServlet {


    HashMap<String, MethodAttributes> annotationMap = new HashMap<String, MethodAttributes>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply("GET",req, resp);
    }

    @Override

    public void init(ServletConfig config) throws ServletException {
        try {
            Iterable<Class> classes = AnnotationScanUtils.getClasses("ro.z2h.controller");
            for (Class aClass : classes) {
               if(aClass.isAnnotationPresent(MyController.class)){

                   System.out.println(aClass.toString());
                   MyController ctrl = (MyController)aClass.getAnnotation(MyController.class);
                   ctrl.urlPath();

                   Method[] methods = aClass.getMethods();
                   for (Method method : methods) {
                       if(method.isAnnotationPresent(MyRequestMethod.class)){
                           MyRequestMethod meth = (MyRequestMethod)method.getAnnotation(MyRequestMethod.class);
                           System.out.println(meth.methodType()+meth.urlPath());
                            String key = ctrl.urlPath()+meth.urlPath();

                            MethodAttributes methodAttributes =new MethodAttributes();

                           methodAttributes.setControllerClass(aClass.getName());
                           methodAttributes.setMethodType(meth.methodType());
                           methodAttributes.setMethodName(method.getName());

                           annotationMap.put(key,methodAttributes);

                       }
                   }
               }
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply("POST",req, resp);
    }

    private void dispatchReply(String httpMethod,HttpServletRequest req, HttpServletResponse resp) throws IOException {
        /*Dispatch*/
        Object r = dispatch(httpMethod, req, resp);

        /*Reply*/
        reply(r,req,resp);

        /*Tratare erori*/
        Exception ex =null;
        sendException(ex,req,resp);
    }

    /*Where an apllication controller should be called*/
    private Object dispatch(String httpMethod, HttpServletRequest req, HttpServletResponse resp) {
        /*pt  /test = Hello*/
        /*pt /employee => all Employees de la Application Controller*/
        String pathInfo = req.getPathInfo();
        MethodAttributes methodAttributes=annotationMap.get(pathInfo);


        try {
            if(methodAttributes !=null){
                Class<?> appControllerClass = Class.forName(methodAttributes.getControllerClass());
                Object appControllerInstance = appControllerClass.newInstance();
                Method controllerMethod = appControllerClass.getMethod(methodAttributes.getMethodName());
                return controllerMethod.invoke(appControllerInstance);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }



        /*if(pathInfo.startsWith("/employee")){
            EmployeeController controller = new EmployeeController();
            return controller.getAllEmployees();

        }
        else if(pathInfo.startsWith("/department")){
            DepartmentController  departmentcontroller = new DepartmentController();
            return departmentcontroller.getAllDepartments();
        }*/
        return "Hello";
    }


    /*Used to send the view to the client.*/
    private void reply(Object r, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        String valueAsString =  objectMapper.writeValueAsString(r);
        writer.printf(valueAsString);
    }

    private void sendException(Exception ex, HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("There was an exception!");
    }
}