package ru.itis.demo.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.itis.demo.dto.SearchForm;
import ru.itis.demo.dto.UserDto;
import ru.itis.demo.models.User;
import ru.itis.demo.repositories.UserRepository;
import ru.itis.demo.services.intrfases.UserSearchService;

@Service
@RequiredArgsConstructor
public class UserSearchServiceImpl implements UserSearchService {
    private final UserRepository usersRepository;

    @Override
    public Page<UserDto> findAllByRequestBody(SearchForm searchForm) {
        PageRequest pageRequest = PageRequest.of(searchForm.getPage() - 1, searchForm.getSize(), Sort.unsorted());
        Page<User> userList = usersRepository.findAllByUsernameIgnoreCase(searchForm.getName(), pageRequest);
        return userList.map(UserDto::from);
    }
}
