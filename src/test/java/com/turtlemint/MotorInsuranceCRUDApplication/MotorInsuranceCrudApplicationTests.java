package com.turtlemint.MotorInsuranceCRUDApplication;

import com.turtlemint.MotorInsuranceCRUDApplication.model.Profile;
import com.turtlemint.MotorInsuranceCRUDApplication.repository.ProfileRepository;
import com.turtlemint.MotorInsuranceCRUDApplication.service.ProfileService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class MotorInsuranceCrudApplicationTests {

	@Autowired
	private ProfileService profileService;

	@MockBean
	private ProfileRepository profileRepository;

	@Test
	public void getAllProfilesTest() {
		Profile profileTest1 = new Profile(12345, "FW", "suzuki", "swift");
		Profile profileTest2 = new Profile(67890, "FW", "suzuki", "ertiga");
		when(profileRepository.findAll()).thenReturn(Stream.of(profileTest1, profileTest2).collect(Collectors.toList()));
		assertEquals(2, profileService.getAllProfiles().size());
	}

	@Test
	public void getProfileTest(){
		long requestId = 394339;
		Profile profile = new Profile(394339, "FW", "suzuki", "baleno");
		when(profileRepository.findById(requestId)).thenReturn(profile);
		assertEquals(profile, profileService.getProfile(requestId));
	}

	@Test
	public void createProfileTest(){
		Profile profile = new Profile(12345, "FW", "kia", "seltos");
		when(profileRepository.save(profile)).thenReturn(profile);
		assertEquals(profileService.createProfile(profile), profile.getRequestId());
	}
}
