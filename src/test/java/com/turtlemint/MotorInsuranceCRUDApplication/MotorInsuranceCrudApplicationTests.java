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
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class MotorInsuranceCrudApplicationTests {

	@Autowired
	private ProfileService profileService;

	@MockBean
	private ProfileRepository profileRepository;

	// JUnit Unit Tests
	@Test
	public void getAllProfilesTest() {
		Profile profileTest1 = new Profile(12345, "FW", "suzuki", "swift");
		Profile profileTest2 = new Profile(67890, "FW", "suzuki", "ertiga");
		when(profileRepository.findAll()).thenReturn(Stream.of(profileTest1, profileTest2).collect(Collectors.toList()));
		assertEquals(2, profileService.getAllProfiles().size());
	}

	@Test
	public void getProfileTest(){
		Profile profile = new Profile(12345, "FW", "suzuki", "baleno");
		when(profileRepository.findById(profile.getRequestId())).thenReturn(profile);
		assertEquals(profile, profileService.getProfile(profile.getRequestId()));
	}

	@Test
	public void createProfileTest(){
		Profile profile = new Profile(12345, "FW", "kia", "seltos");
		when(profileRepository.save(profile)).thenReturn(profile);
		assertEquals(profileService.createProfile(profile), profile);
	}

	@Test
	public void updateProfileTest(){
		Profile profileTest1 = new Profile(12345, "FW", "suzuki", "swift");
		Profile profileTest2 = new Profile(12345, "FW", "suzuki", "ertiga");
		when(profileRepository.save(profileTest2)).thenReturn(profileTest2);
		assertEquals(profileService.updateProfile(profileTest1.getRequestId(), profileTest2), profileTest2);
	}

	@Test
	public void deleteProfileTest(){
		Profile profile = new Profile(12345, "FW", "kia", "seltos");
		profileService.deleteProfile(profile.getRequestId());
		verify(profileRepository, times(1)).deleteById(profile.getRequestId());
	}
}
