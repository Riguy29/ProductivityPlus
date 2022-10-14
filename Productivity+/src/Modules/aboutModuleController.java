package Modules;

import java.net.URL;
import java.util.ResourceBundle;

public class aboutModuleController extends baseModuleInitalizer {
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)  {
		super.initialize(arg0, arg1);
		baseController.setTitle("About");
	}
}
