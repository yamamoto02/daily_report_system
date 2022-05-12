package actions;

import java.io.IOException;

import javax.servlet.ServletException;

import constants.AttributeConst;
import constants.ForwardConst;

public class TopAction extends ActionBase {
    /**
     * indexメソッドを実行する
     */
    @Override
    public void process() throws ServletException, IOException {

        //メソッドを実行
        invoke();

    }

    /**
     * 一覧画面を表示する
     */
    public void index() throws ServletException, IOException{

        String flush = getSessionScope(AttributeConst.FLUSH);
        if(flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

        forward(ForwardConst.FW_TOP_INDEX);
    }
}
