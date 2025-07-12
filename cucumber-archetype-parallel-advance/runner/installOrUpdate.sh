echo "Pulling latest engine changes from gitlab"
git pull

echo "Building image"
cd ..
docker build -t e2e .

echo "Updated sucessfully"