package com.example.narrative;

import static org.junit.Assert.*;
import java.util.List;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.example.narrative.Repository.RegistrationRepository;
import com.example.narrative.model.Register;

import jakarta.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
class NarrativeApplicationTests {
	@Autowired
	private RegistrationRepository RegistrationRepository;

	@Test
	void contextLoads() {
	}

	public Register createTest(String registerName, String mailAddress, String phoneNum, boolean carmediumHave, String registASession, String reserveBook) {
		Register record = new Register();
		record.setRegisterName(registerName);
		record.setMailAddress(mailAddress);
		record.setPhoneNum(phoneNum);
		record.setCarmediumHave(carmediumHave);
		record.setSchoolApart("無");
		record.setRegistDate(java.time.LocalDateTime.now());
		record.setRegistASession(registASession);
		record.setReserveBook(reserveBook);
		record.setUpdatedAt(java.time.LocalDateTime.now());

		return record;
	}

	@Transactional
	@Rollback
	@Test
	public void testShowAllRegister() {
		Register register1 = createTest("Amy", "Amy@gmail.com", "0911223456", true, "A場次", "A書");
		RegistrationRepository.save(register1);

		Register register2 = createTest("Ben", "Ben@yahoo.com.tw", "0998765321", false, "B場次", "B書");
		RegistrationRepository.save(register2);

		RegistrationRepository.flush(); // ← 強制 flush，有助於避免 Hibernate 延遲寫入導致的錯誤

		// Spring 嘗試去找一個 allId 的欄位，但 Register 顯然沒有這個欄位，所以它就報錯了。
		// List<Register> allRegisters = RegistrationRepository.findByAllId();
		List<Register> allRegisters = RegistrationRepository.findAll();

		List<String> names = allRegisters.stream().map(Register::getRegisterName).toList();
    	assertTrue(names.contains("Amy"));
    	assertTrue(names.contains("Ben"));
	}
}