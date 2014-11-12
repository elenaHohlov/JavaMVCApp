package ro.z2h;

import org.codehaus.jackson.map.ObjectMapper;
import ro.z2h.annotation.MyController;
import ro.z2h.annotation.MyRequestMethod;
import ro.z2h.controller.DepartmentController;
import ro.z2h.controller.EmployeeController;
import ro.z2h.fmk.AnnotationScanUtils;
import ro.z2h.fmk.MethodAttributes;

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
 * Created by Computer on 11/11/2014.
 */
public class MyDispatcherServlet extends HttpServlet {

   public HashMap<String, MethodAttributes> hashMap = new HashMap<String, MethodAttributes>();

    @Override
    public void init() throws ServletException {

        try {
           Iterable<Class> classes = AnnotationScanUtils.getClasses("ro.z2h.controller");

            for (Class aClass : classes) {
                System.out.println(aClass.getName());
//                Daca poate fi controller
                if(aClass.isAnnotationPresent(MyController.class))
                {
                    MyController ctrlannotation = (MyController)aClass.getAnnotation(MyController.class);

//                ce request poate procesa
                    System.out.println(ctrlannotation.urlPath());
//                    Identificare metode care proceseaza bussiness
                    Method[] methods = aClass.getMethods();
                    for (Method method : methods)
                    {
                        if (method.isAnnotationPresent(MyRequestMethod.class))
                        {
                            MyRequestMethod methodAnnotation = (MyRequestMethod) method.getAnnotation(MyRequestMethod.class);
                            System.out.println(methodAnnotation.urlPath()+ " "+ methodAnnotation.methodType());

                            MethodAttributes mta = new MethodAttributes();
                            mta.setControllerClass(aClass.getName());
                            mta.setMethodName(method.getName());
                            mta.setMethodType(methodAnnotation.methodType());
                            String key = ctrlannotation.urlPath() + methodAnnotation.urlPath();
                            hashMap.put(key, mta);
                            System.out.println(hashMap.get(key));
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
//        Delegate to someone (an application controller)
        dispatchReply("POST",req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //        Delegate to someone (an application controller)
        dispatchReply("GET",req, resp);
    }

    private void dispatchReply(String  httpMethod,HttpServletRequest req, HttpServletResponse resp) {
//        dispatch- delegare catre un app controller si asteptare unui raspuns
       Object r = dispatch(req, resp);

//        Transmiterea raspunsului catre client

        try {
            reply(r, req, resp);
        } catch (Exception e) {

 //     Transmitere erori
            sendException (e, req, resp);
        }


    }

    private void sendException(Exception ex, HttpServletRequest req, HttpServletResponse resp) {

//        tratare exceptie

    }

    //       Unde ar trebui apelat un app controller
    private Object dispatch(HttpServletRequest req, HttpServletResponse resp) {

        String pathInfo = req.getPathInfo();

      MethodAttributes methodAttributes =  hashMap.get(pathInfo);
        try {
            if( methodAttributes!= null) {
                Class<?> appControllerClass = Class.forName(methodAttributes.getControllerClass());
                Object appControllerInstance = appControllerClass.newInstance();
                Method controllerMethod = appControllerClass.getMethod(methodAttributes.getMethodName());
                return controllerMethod.invoke(appControllerInstance);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

//    if (pathInfo.startsWith("/employee"))
//    {
//       EmployeeController employeeController= new EmployeeController();
//       return employeeController.getAllEmployees();
//
//    }else if
//            (pathInfo.startsWith("/department")){
//        DepartmentController controller= new DepartmentController();
//        return controller.getAllDepartments();
//    }

//        Stabilire controller in functie de pathInfo
        return "HELLO";
    }


    private void reply(Object r, HttpServletRequest req, HttpServletResponse resp) throws Exception{
       PrintWriter out = resp.getWriter();

        ObjectMapper objectMapper = new ObjectMapper();
        String valueAsString = objectMapper.writeValueAsString(r);
        out.printf(valueAsString);
    }


}
