import static org.hamcrest.Matchers.isA;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.times;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.verifyPrivate;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.verification.PrivateMethodVerification;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.danidemi.tutorial.tdd.showcase.hamcrest.Invoice;
import com.danidemi.tutorial.tdd.showcase.hamcrest.InvoiceStatus;
import com.danidemi.tutorial.tdd.showcase.hamcrest.PayedStatus;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Invoice.class)
public class StatusTest {

	@Test
	@Ignore
	public void testInvoice() throws Exception {


		InvoiceStatus invoice = mock(InvoiceStatus.class);

		invoice.pay();
		
		PrivateMethodVerification verifyPrivate = verifyPrivate(invoice,
				times(2));
		verifyPrivate.invoke("transitionTo", argThat(isA(PayedStatus.class)));

	}

}
