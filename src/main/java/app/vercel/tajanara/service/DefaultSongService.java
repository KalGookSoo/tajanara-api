package app.vercel.tajanara.service;

import app.vercel.tajanara.domain.Song;
import app.vercel.tajanara.dto.request.CreateSongRequest;
import app.vercel.tajanara.dto.response.SongResponse;
import app.vercel.tajanara.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class DefaultSongService implements SongService {

    private final SongRepository songRepository;

    @Override
    public SongResponse createSong(CreateSongRequest request) {
        Song song = Song.builder()
                .title(request.getTitle())
                .artist(request.getArtist())
                .lyrics(request.getLyrics())
                .build();
        songRepository.save(song);
        return new SongResponse(song);
    }

    @Override
    public Page<SongResponse> getSongs(Pageable pageable) {
        Page<Song> page = songRepository.findAll(pageable);
        return page.map(SongResponse::new);
    }

    @Override
    public SongResponse getSongById(String id) {
        Song song = songRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 노래: " + id));
        return new SongResponse(song);
    }

    @Override
    public SongResponse updateSongById(String id, CreateSongRequest request) {
        Song song = Optional.ofNullable(songRepository.getReferenceById(id))
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 노래: " + id));
        song.update(
                request.getTitle(),
                request.getArtist(),
                request.getLyrics()
        );
        songRepository.save(song);
        return new SongResponse(song);
    }

    @Override
    public void deleteSongById(String id) {
        songRepository.deleteById(id);
    }

    @Override
    public void initialDefaultSongs() {
        log.info("노래 정보를 초기화합니다.");
        createSong(new CreateSongRequest("애국가", "안익태", "동해물과 백두산이 마르고 닳도록\n하느님이 보우하사 우리나라 만세\n무궁화 삼천리 화려강산\n대한사람 대한으로 길이 보전하세\n남산 위에 저 소나무 철갑을 두른 듯\n바람서리 불변함은 우리 기상일세\n무궁화 삼천리 화려강산\n대한사람 대한으로 길이 보전하세\n가을 하늘 공활한데 높고 구름 없이\n밝은 달은 우리 가슴 일편단심일세\n무궁화 삼천리 화려강산\n대한사람 대한으로 길이 보전하세\n이 기상과 이 맘으로 충성을 다하여\n괴로우나 즐거우나 나라 사랑하세\n무궁화 삼천리 화려강산\n대한사람 대한으로 길이 보전하세"));
        createSong(new CreateSongRequest("바운스", "조용필", "그대가 돌아서면 두 눈이 마주칠까\n심장이 바운스 바운스 두근대\n들릴까봐 겁나\n한참을 망설이다 용기를 내\n밤새워 준비한 순애보 고백해도 될까\n처음 본 순간부터 네 모습이\n내 가슴 울렁이게 만들었어\n베이비 유어 마이 트램폴린\n유 메이크 미 바운스 바운스\n수많은 인연과 바꾼 너인 걸\n사랑이 남긴 상처들도 감싸줄게\n어쩌면 우린 벌써 알고 있어\n그토록 찾아 헤맨 사랑의 꿈\n외롭게만 하는 걸\n유 메이크 미 바운스 유 메이크 미 바운스\n바운스 바운스\n망설여져 나 혼자만의 감정일까\n내가 잘못 생각한거라면\n어떡하지 눈물이나\n별처럼 반짝이는 눈망울도\n수줍어 달콤하던 네 입술도\n내겐 꿈만 같은 걸\n유 메이크 미 바운스\n어쩌면 우린 벌써 알고 있어\n그토록 찾아 헤맨 사랑의 꿈\n외롭게만 하는걸 어쩌면 우린 벌써\n유 메이크 미 유 메이크 미"));
        createSong(new CreateSongRequest("링딩동", "샤이니", "베이베, 네게 반해버린 내게\n왜 이래 두렵다고? 물러서지 말고?\n그냥 내게 맡겨봐라, 어때, 마이 레이디?\n링딩동-링딩동, 링디기-딩디기-딩딩딩\n링딩동-링딩동, 링디기-딩디기-딩딩딩\n링딩동-링딩동, 링디기-딩디기-딩딩딩\n링딩동-링딩동, 링디기-딩디기-딩딩딩\n버터플라이, 너를 만난 첫 순간\n눈이 번쩍 머린 스탑, 벨이 딩동 울렸어\n난 말야 멋진 놈, 착한 놈\n그런 놈은 아니지만 나름대로 괜찮은 밷-보이\n너는 마치 버터플라이, 너무 약해 빠졌어\n너무 순해 빠졌어 널 곁에 둬야겠어\n더는 걱정 마, 걱정 마, 나만 믿어보면 되잖아\n네가 너무 맘에 들어 놓칠 수 없는 걸\n베이베, 내 가슴을 멈출 수\n오, 크레이지, 너무 예뻐 견딜 수\n오, 크레이지, 너 아니면 필요 없다\n크레이지, 나 왜 이래?\n위 워너 고 로카, 로카, 로카, 로카, 로카 (쏘 판타스틱)\n고 로카, 로카, 로카, 로카, 로카 (쏘 엘라스틱)\n판타스틱, 판타스틱, 판타스틱, 판타스틱\n엘라스틱, 엘라스틱, 엘라스틱, 엘라스틱\n링딩동-링딩동, 링디기-딩디기-딩딩딩 (오직 너만 들린다)\n링딩-동링-딩동, 링디기-딩디기-딩딩딩 (머릿 속에 울린다)\n링딩동-링딩동, 링디기-딩디기-딩딩딩 (내 가슴에 울린다)\n링딩동-링딩동, 링디기-딩디기-딩딩딩\n아이 콜드 유 버터플라이\n날이 가면, 갈수록 못이 박혀, 너란 걸\n헤어날 수 없다는 걸, 나를 선택해 (돌이키지 말고)\n선택해 (도망가지 말고), 네게 빠진 바보인 나 (날 책임 져야 돼)\n베이베 (헤이), 내 가슴을 멈출 수\n오, 크레이지 (헤이), 너무 예뻐 견딜 수\n오, 크레이지, 너 아니면 필요 없다\n크레이지, 나 왜 이래?\n난 착하디 착한 증후군이 걸린 너를 이해 못하겠다\n넌 가끔씩 그런 고정 이미지를 탈피 이탈해봐, 괜찮다\n브레이크 아웃 (헤이), 브레이크 아웃 (헤이)\n브레이크 아웃 (헤이), 브레이크 아웃 (헤이)\n링딩딩딩딩, 디-디-디-디-디-디-디-디\n동-동-동-동-동-동-동-동\n사실, 난 불안해 어떻게 날 보는지\n어쩌면, 어쩌면 내게 호감을 갖고 있는지 몰라\n이토록 안절부절 할 수밖에 없어\n돌이킬 수 없는 걸\n컴플리케이트 걸 (절대 노란 대답 하지 마)\n나 괜찮은 남자란 걸 (내가 미쳐버릴지 몰라)\n돈트 비 실리 걸 (실리 걸), 유어 마이 미라클 (마이 미라클)\n너만 가질 수 있다면, 내겐 다 필요 없는걸\n베이베 (헤이), 내 가슴을 멈출 수\n오, 크레이지 (헤이), 너무 예뻐 견딜 수\n오, 크레이지, 너 아니면 필요 없다\n크레이지, 나 왜 이래?\n위 워너 고 로카, 로카, 로카, 로카, 로카 (쏘 판타스틱)\n고 로카, 로카, 로카, 로카, 로카 (쏘 엘라스틱)\n판타스틱, 판타스틱, 판타스틱, 판타스틱\n엘라스틱, 엘라스틱, 엘라스틱, 엘라스틱\n링딩동-링딩동, 링디기-딩디기-딩딩딩 (오직 너만 들린다)\n링딩-동링-딩동, 링디기-딩디기-딩딩딩 (머릿 속에 울린다)\n링딩동-링딩동, 링디기-딩디기-딩딩딩 (내 가슴에 울린다)\n링딩동-링딩동, 링디기-딩디기-딩딩딩"));
    }

}
