package com.miniproject.eventure.usecase.event.impl;

import com.miniproject.eventure.common.exeptions.DataNotFoundException;
import com.miniproject.eventure.entity.event.Event;
import com.miniproject.eventure.entity.event.EventCategory;
import com.miniproject.eventure.entity.geography.City;
import com.miniproject.eventure.entity.user.User;
import com.miniproject.eventure.infrastructure.event.dto.CreateEventRequestDTO;
import com.miniproject.eventure.infrastructure.event.repository.EventCategoryRepository;
import com.miniproject.eventure.infrastructure.event.repository.EventRepository;
import com.miniproject.eventure.infrastructure.geography.repository.CityRepository;
import com.miniproject.eventure.infrastructure.user.repository.UserRepository;
import com.miniproject.eventure.usecase.event.CreateEventUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateEventUseCaseImpl implements CreateEventUseCase {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EventCategoryRepository eventCategoryRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    EventRepository eventRepository;

    @Override
    public Event createEvent(CreateEventRequestDTO req) {
        Event newEvent = req.toEntity();

        User user = userRepository.findById(req.getOrganizerId())
                .orElseThrow(() -> new DataNotFoundException("User not found !"));
        newEvent.setOrganizerId(user);

        EventCategory eventCategory = eventCategoryRepository.findById(req.getEventCategoryId())
                .orElseThrow(() -> new DataNotFoundException("Event category ID not found !"));
        newEvent.setEventCategoryId(eventCategory);

        City city = cityRepository.findById(req.getCityId())
                .orElseThrow(() -> new DataNotFoundException("City ID not found !"));
        newEvent.setCityId(city);

        return eventRepository.save(newEvent);
    }
}