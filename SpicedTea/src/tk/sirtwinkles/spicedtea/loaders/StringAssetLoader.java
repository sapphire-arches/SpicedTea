package tk.sirtwinkles.spicedtea.loaders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.utils.Array;

public class StringAssetLoader extends SynchronousAssetLoader<String, AssetLoaderParameters<String>> {

	public StringAssetLoader() {
		super(new InternalFileHandleResolver());
	}

	@SuppressWarnings("resource")
	@Override
	public String load(AssetManager assetManager, String fileName,
			AssetLoaderParameters<String> parameter) {
		if (Gdx.app.getType() == ApplicationType.Desktop) {
			fileName = "./bin/" + fileName;
		}
		try {
			FileReader f = new FileReader(resolve(Gdx.files.getLocalStoragePath() + fileName).file());
			BufferedReader b = new BufferedReader(f);
			StringBuilder sb = new StringBuilder();
			while (b.ready()) {
				sb.append(b.readLine());
				sb.append("\n");
			}
			return sb.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Bombs Away!";
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Array<AssetDescriptor> getDependencies(String fileName,
			AssetLoaderParameters<String> parameter) {
		return null;
	}
	
}