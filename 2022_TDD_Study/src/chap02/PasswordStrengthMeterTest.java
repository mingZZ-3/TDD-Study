package chap02;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

public class PasswordStrengthMeterTest {
	
	private PasswordStrengthMeter meter = new PasswordStrengthMeter();
	
	private void assertStrength(String password, PasswordStrength expStr) {
		PasswordStrength result = meter.meter(password);
		assertEquals(expStr, result);
	}
	
	// 전부 충족 
	@Test
	void meetsAllCriteria_then_Strong() {
		assertStrength("ab12!@AB", PasswordStrength.STRONG);
		assertStrength("abc1!Add", PasswordStrength.STRONG);
	}
	
	// 2개만 충족 
	@Test
	void meetsOtherCriteria_except_for_Length_Then_Normal() {
		assertStrength("ab12!@A", PasswordStrength.NORMAL);
	}
	
	@Test
	void meetsOtherCreiteria_except_for_number_Then_Normal() {
		assertStrength("ab!@ABqwer", PasswordStrength.NORMAL);
	}
	
	@Test
	void meetsOtherCreiteria_except_for_Uppercase_Then_Normal() {
		assertStrength("ab1!@qwer", PasswordStrength.NORMAL);
	}
	
	// 1개만 충족 
	@Test
	void meetsOnlyLengthCriteria_Then_Weak() {
		assertStrength("asfrecdds", PasswordStrength.WEAK);
	}
	
	@Test
	void meetsOnlyNumCriteria_Then_Weak() {
		assertStrength("12345", PasswordStrength.WEAK);
	}
	
	@Test
	void meetsOnlyUpperCriteria_Then_Weak() {
		assertStrength("ABCDF", PasswordStrength.WEAK);
	}
	
	// null, empty
	@Test
	void nullInput_Then_Invalid() {
		assertStrength(null, PasswordStrength.INVALID);
	}
	
	@Test
	void emptyInput_Then_Invalid() {
		assertStrength("", PasswordStrength.INVALID);
	}
	
	
}
