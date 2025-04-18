package com.example.narrative;

import static org.junit.Assert.*;
import java.util.List;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.example.narrative.entity.RegistRecord;
import com.example.narrative.repository.RegistrationRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
class NarrativeApplicationTests {
	@Autowired
	private RegistrationRepository registrationRepository;

	@Test
	void contextLoads() {
	}

	public RegistRecord createTest(String registerName, String mailAddress, String phoneNum, boolean carmediumHave, String registASession, String reserveBook) {
		RegistRecord record = new RegistRecord();
		record.setRegisterName(registerName);
		record.setMailAddress(mailAddress);
		record.setPhoneNum(phoneNum);
		record.setSchoolApart("無");
		record.setRegistDate(java.time.LocalDateTime.now());
		record.setUpdatedAt(java.time.LocalDateTime.now());

		return record;
	}

	@Transactional
	@Rollback
	@Test
	public void testShowAllRegister() {
		RegistRecord registRecord1 = createTest("Amy", "Amy@gmail.com", "0911223456", true, "A場次", "A書");
		registrationRepository.save(registRecord1);

		RegistRecord registRecord2 = createTest("Ben", "Ben@yahoo.com.tw", "0998765321", false, "B場次", "B書");
		registrationRepository.save(registRecord2);

		registrationRepository.flush(); // ← 強制 flush，有助於避免 Hibernate 延遲寫入導致的錯誤

		// Spring 嘗試去找一個 allId 的欄位，但 RegistRecord 顯然沒有這個欄位，所以它就報錯了。
		// List<RegistRecord> allRegistRecords = RegistrationRepository.findByAllId();
		List<RegistRecord> allRegistRecords = registrationRepository.findAll();

		List<String> names = allRegistRecords.stream().map(RegistRecord::getRegisterName).toList();
    	assertTrue(names.contains("Amy"));
    	assertTrue(names.contains("Ben"));
	}
}