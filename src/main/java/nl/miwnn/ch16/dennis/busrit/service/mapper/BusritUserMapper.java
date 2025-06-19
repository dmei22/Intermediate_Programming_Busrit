package nl.miwnn.ch16.dennis.busrit.service.mapper;

// Convert NewBusritUserDTO object into BusritUser

import nl.miwnn.ch16.dennis.busrit.dto.NewBusritUserDTO;
import nl.miwnn.ch16.dennis.busrit.model.BusritUser;

public class BusritUserMapper {

    public static BusritUser fromDto(NewBusritUserDTO newBusritUserDTO) {
        BusritUser busritUser = new BusritUser();
        busritUser.setUsername(newBusritUserDTO.getUsername());
        busritUser.setPassword(newBusritUserDTO.getPassword());

        return busritUser;
    }
}
