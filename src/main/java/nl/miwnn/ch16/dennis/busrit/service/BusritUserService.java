package nl.miwnn.ch16.dennis.busrit.service;

import nl.miwnn.ch16.dennis.busrit.dto.NewBusritUserDTO;
import nl.miwnn.ch16.dennis.busrit.model.BusritUser;
import nl.miwnn.ch16.dennis.busrit.repositories.BusritUserRepository;
import nl.miwnn.ch16.dennis.busrit.service.mapper.BusritUserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class BusritUserService implements UserDetailsService {

    private final BusritUserRepository busritUserRepository;
    private final PasswordEncoder passwordEncoder;

    public BusritUserService(BusritUserRepository busritUserRepository, PasswordEncoder passwordEncoder) {
        this.busritUserRepository = busritUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return busritUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User: %s was not found", username)));
    }

    public void saveUser(BusritUser busritUser) {
        busritUser.setPassword(passwordEncoder.encode(busritUser.getPassword()));
        busritUserRepository.save(busritUser);
    }

    public List<BusritUser> getAllUsers() {
        return busritUserRepository.findAll();
    }

    public boolean usernameInUse(String username) {
        return busritUserRepository.existsByUsername(username);
    }

    public void save (NewBusritUserDTO newBusritUserDTO) {
        saveUser(BusritUserMapper.fromDto(newBusritUserDTO));
    }
}
