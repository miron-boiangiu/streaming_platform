package streamingplatform.command;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import input.ActionInput;
import streamingplatform.StreamingPlatform;
import streamingplatform.page.Page;
import streamingplatform.page.PageFactory;
import streamingplatform.user.User;

public class ChangePageCommand extends Command{
    public ChangePageCommand(ActionInput action) {
        super(action);
    }

    @Override
    public void execute() {
        StreamingPlatform site = StreamingPlatform.getInstance();
        Page currentPage = site.getCurrentPage();

        if(currentPage.getAccessiblePages().contains(action.getPage())){
            site.setCurrentPage(PageFactory.create(action.getPage()));
            if(action.getPage().equals("movies") || action.getPage().equals("see details")){
                site.addOutputNode();
            }
            else if(action.getPage().equals("logout")){
                site.setCurrentPage(PageFactory.create("unauthhome"));
                site.setCurrentUser(null);
            }
        }
        else{
            site.addErrorOutputNode();
        }
    }
}