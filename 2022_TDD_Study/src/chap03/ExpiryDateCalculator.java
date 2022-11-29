package chap03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {
	
    public LocalDate calculateExpiryDate(PayData payData) {
    	// 몇개월치
        int addedMonths = payData.getPayAmount() == 100_000 ?
                12 : payData.getPayAmount() / 10_000;
        
        // 첫납부일 유무 
        if (payData.getFirstBillingDate() != null) {
            return expiryDateUsingFirstBillingDate(payData, addedMonths);
        } else {
            return payData.getBillingDate().plusMonths(addedMonths);
        }
    }

    // 첫납부일로 만료일 계산
    private LocalDate expiryDateUsingFirstBillingDate(PayData payData, int addedMonths) {
    	// 단순 만료일 계산
        LocalDate candidateExp =
                payData.getBillingDate().plusMonths(addedMonths);
        // 첫납부일의 day
        final int dayOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();
        
        // 첫납부일의 day != 단순계산 만료일의 day
        if (dayOfFirstBilling != candidateExp.getDayOfMonth()) {
        	// 만료일의 해당 월 - 마지막 day
            final int dayLenOfCandiMon = YearMonth.from(candidateExp).lengthOfMonth();
            
            // 계산만료일이 첫납부일 보다 작
            if (dayLenOfCandiMon < dayOfFirstBilling) {
            	//// 계산만료일의 월 마지막 day
                return candidateExp.withDayOfMonth(dayLenOfCandiMon);}
            
            //// 첫납부일의 day 그대로 
            return candidateExp.withDayOfMonth(dayOfFirstBilling);
        } else {
        	//// 첫납부일의 day == 단순계산 만료일의 day (1-28)
            return candidateExp;
        }
    }
}
