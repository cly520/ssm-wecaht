package cy.ly.message;

import cy.ly.dto.MusicDTO;

/**
 * 音乐消息
 */
public class MusicMessage extends BaseMessage {
	// 音乐
	private MusicDTO MusicDTO;

	public MusicDTO getMusicDTO() {
		return MusicDTO;
	}

	public void setMusicDTO(MusicDTO musicDTO) {
		MusicDTO = musicDTO;
	}
}