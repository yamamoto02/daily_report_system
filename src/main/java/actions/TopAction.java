package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.EmployeeView;
import actions.views.ReportView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import services.ReportService;

public class TopAction extends ActionBase {

    private ReportService service;

    @Override
    public void process() throws ServletException, IOException {

        service = new ReportService();
        //メソッドを実行
        invoke();

        service.close();

    }

    /**
     * 一覧画面を表示する
     */
    public void index() throws ServletException, IOException{

        EmployeeView loginEmployee = (EmployeeView)getSessionScope(AttributeConst.LOGIN_EMP);

        int page = getPage();
        List<ReportView> reports = service.getMinePerPage(loginEmployee, page);

        long myReportsCount = service.countAllMine(loginEmployee);

        putRequestScope(AttributeConst.REPORTS, reports);
        putRequestScope(AttributeConst.REP_COUNT, myReportsCount);
        putRequestScope(AttributeConst.PAGE, page);
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE);

        String flush = getSessionScope(AttributeConst.FLUSH);
        if(flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

        forward(ForwardConst.FW_TOP_INDEX);
    }
}
