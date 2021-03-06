package demo;

import org.concordion.api.extension.Extension;
import org.concordion.ext.StoryboardExtension;
import org.concordion.ext.StoryboardExtension.AppendTo;
import org.concordion.ext.storyboard.CardResult;
import org.concordion.ext.storyboard.SectionContainer;
import org.concordion.ext.storyboard.StockCardImage;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

import test.concordion.ext.storyboard.DummyScreenshotTaker;

@RunWith(ConcordionRunner.class)
public class StoryboardDemo {
	@Extension
	public final StoryboardExtension storyboard = new StoryboardExtension()
						.setScreenshotTaker(new DummyScreenshotTaker(1))
						.setAppendMode(AppendTo.EXAMPLE);

	int attempts = 0;
			
	public boolean addCard(final String description) {
		String title = storyboard.getCurrentExampleTitle() + " - " + description;
		
		if (!storyboard.hasScreenshotTaker()) {
			title += " (WITHOUT SCREENSHOTTAKER)";
		}
		
		storyboard.addScreenshot("Adding Card BEFORE", description);
		storyboard.addSectionContainer("Only added if active screenshot taker as empty cntainers not shown");
				
		storyboard.addSectionContainer(title);
		
		storyboard.insertContainer(new SectionContainer("WithOUT Final Screenshot"));
		storyboard.addScreenshot("A", "screenshot");
		storyboard.addNotification("n1", "empty", StockCardImage.EMAIL, CardResult.SUCCESS);
		storyboard.closeContainer();
		
		storyboard.insertSectionContainer("With Final Screenshot");
		
		//TODO 
		// How should we handle this?  
		// Will failfast alter behaviour?
		// Do need to close some/all containers... 
		//Assert.fail("Oh boy!");
		
		//storyboard.skipFinalScreenshot();
		storyboard.addScreenshot("A", "screenshot");
		storyboard.addNotification("n1", "null", StockCardImage.JSON, CardResult.SUCCESS);
		storyboard.addScreenshot("A", "screenshot");
		storyboard.closeContainer();
		
		storyboard.addNotification("n1", "description", StockCardImage.EMAIL, CardResult.SUCCESS);
		storyboard.addScreenshot("s2", "description");
//		storyboard.addSectionContainer("");
		
		storyboard.closeContainer();
		
		storyboard.addScreenshot("ALL DONE", description);
		
		return 1 > attempts ++;
	}

}
