package jcefeclipseplugin.veiws;

import java.awt.Component;
import java.awt.Frame;

import org.cef.CefApp;
import org.cef.CefClient;
import org.cef.browser.CefBrowser;
import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

/**
 * This class corresponds to a UI view in Eclipse which contains the embedded Chrome browser's UI.
 * @author pavana
 *
 */
public class ChromView extends ViewPart {

	// this will hold the browser view
	private Composite mChromeViewComposite;
	
	public ChromView() {
		super();
	}

	/**
	 * This method creates the control for a ViewPart.
	 */
	@Override
	public void createPartControl(Composite parent) {
		
		// instantiate a SWT composite
		mChromeViewComposite = new Composite(parent, SWT.EMBEDDED | SWT.NO_BACKGROUND);
		
		// get the corresponding AWT frame to render the browser, as embedded Chrome cannot be rendered on a SWT component
		Frame frame = SWT_AWT.new_Frame(mChromeViewComposite);
		
		// reference for CefApp; refer https://bitbucket.org/chromiumembedded/java-cef for documentation
		final CefApp cefApp_;
		// reference for CefClent; refer https://bitbucket.org/chromiumembedded/java-cef for documentation
		final CefClient  client_;
		// reference for CefBrowser; refer https://bitbucket.org/chromiumembedded/java-cef for documentation
		final CefBrowser browser_;
		// java awt component to contain the browser UI
		final Component  browserUI_;
		
		try {
			// get the instance of the CefApp
			cefApp_ = CefApp.getInstance();
			// create the CefApp client
			client_ = cefApp_.createClient();
			// create an instance of the Chrome browser
			browser_ = client_.createBrowser("https://www.google.com", false, false);
			// get the browser UI component compatible to AWT
			browserUI_ = browser_.getUIComponent();
			// add the browser UI to the frame
			frame.add(browserUI_);
			frame.pack();
			// make the frame visible
			frame.setVisible(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}

}
