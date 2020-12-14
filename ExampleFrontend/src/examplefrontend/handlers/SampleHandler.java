package examplefrontend.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.jface.dialogs.MessageDialog;

import org.json.simple.*;

import core.test.Test;

public class SampleHandler extends AbstractHandler {

	Test test = new Test();
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		MessageDialog.openInformation(
				window.getShell(),
				"ExampleFrontendPlugin",
				test.stringReturn()+"\n"+stringBuild());
		return null;
	}
	
	public String stringBuild() {
		JSONObject obj = new JSONObject();
		
        obj.put("Where?", "in ExampleFrontend");
        obj.put("Properties?", "Added the path");
        obj.put("Export-Package", "org.json.simple");
                
		return obj.toJSONString();
	}
}
