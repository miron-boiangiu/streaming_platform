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
                ObjectNode newNode = StreamingPlatform.getInstance().getOutput().addObject();
                newNode.putNull("error");
                User currentUser = StreamingPlatform.getInstance().getCurrentUser();
                if(currentUser != null){
                    ObjectNode userNode = newNode.putObject("currentUser");
                    site.getCurrentUser().addOutput(userNode);
                }
                else{
                    newNode.putNull("currentUser");
                }
                site.getCurrentPage().addOutput(newNode);
            }
            //TODO: Check if the new page is Logout!
            if(action.getPage().equals("logout")){
                site.setCurrentPage(PageFactory.create("unauthhome"));
                site.setCurrentUser(null);
            }
        }
        else{
            ObjectNode newNode = StreamingPlatform.getInstance().getOutput().addObject();
            newNode.put("error", "Error");
            newNode.putNull("currentUser");
            newNode.putArray("currentMoviesList");
        }
    }
}
