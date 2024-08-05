import defaultProfileImage from "@/assets/profile.png"
export function ProfileCard({user}){
    return (
        <div className="card">
            <div className="card-header text-center">
                <img
                    className="img-fluid rounded-circle shadow-sm"
                    src={defaultProfileImage}
                    alt="profile image"
                    width="200"
                />
            </div>
            <div className="card-body text-center">
                <span className="fs-3">{user.username}</span>
            </div>
        </div>
    )
}