package com.danidemi.tutorial.tdd.showcase.powermock;
import static org.hamcrest.Matchers.isA;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.times;
import static org.powermock.api.mockito.PowerMockito.*;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.verification.PrivateMethodVerification;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.danidemi.tutorial.tdd.showcase.accounting.Invoice;
import com.danidemi.tutorial.tdd.showcase.accounting.PayedStatus;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Invoice.class)
public class StatusTest {

	@Ignore
	@Test
	public void testInvoice() throws Exception {


		//Invoice.class.getMethod("transitionTo", parameterTypes)
		
		Invoice invoice = mock(Invoice.class);
		//Invoice invoice = spy( new Invoice(100L, 7800));

		invoice.pay();
		
		PrivateMethodVerification verifyPrivate = verifyPrivate(invoice,
				times(2));
		verifyPrivate.invoke("transitionTo", argThat(isA(PayedStatus.class)));
		//verifyPrivate.invoke("transitionTo", InvoiceStatus.class);

	}

}
